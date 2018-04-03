package com.example.escproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
	private RecyclerView viewList;
	private QuizAdapter mAdapter;
	private List<Quiz> quizzes;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	private View view;
	
	public QuizFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_quiz, container, false);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this.getActivity(), LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("Courses")
				.child(MyCourseActivity.state.courID)
				.child("Quiz")
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						quizzes = new ArrayList<>();
						
						Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
						while(itr.hasNext()) {
							DataSnapshot dss = itr.next();
							String state = (String)dss.child("state").getValue();
							String tmp = dss.getKey();
							quizzes.add(new Quiz(tmp, state));
						}
						viewList = view.findViewById(R.id.recyclerViewQuiz);
						LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
						viewList.setLayoutManager(linearLayoutManager);
						mAdapter = new QuizAdapter(view.getContext(), quizzes);
						viewList.setAdapter(mAdapter);
						viewList.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener(){
							@Override
							public void onItemClick(final View view, final int position) {
								databaseReference.child("users")
										.child(CourseActivity.userType)
										.child(firebaseUser.getUid())
										.child("Course")
										.child(MyCourseActivity.state.courID)
										.child("Quiz").addListenerForSingleValueEvent(new ValueEventListener() {
									@Override
									public void onDataChange(DataSnapshot dataSnapshot) {
										if(dataSnapshot.hasChild(mAdapter.quizzes.get(position).ID)||mAdapter.quizzes.get(position).state.equals("open")) {
											Intent intent = new Intent(view.getContext(), MyQuizActivity.class);
											MyQuizActivity.state = new Quiz(mAdapter.quizzes.get(position).ID, MyCourseActivity.state);
											intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
											startActivity(intent);
										} else {
											Toast.makeText(view.getContext(),"This quiz is closed now", Toast.LENGTH_LONG).show();
										}
									}
									
									@Override
									public void onCancelled(DatabaseError databaseError) {
									
									}
								});
							}
						}));
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
		// Inflate the layout for this fragment
		return view;
	}
	
}
