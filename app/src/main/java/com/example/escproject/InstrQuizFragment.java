package com.example.escproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
public class InstrQuizFragment extends Fragment {
	private RecyclerView viewList;
	private QuizManiAdapter mAdapter;
	private List<String> quizzes;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	private View view;
	Button upload_quiz;
	
	public InstrQuizFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_instr_quiz, container, false);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this.getActivity(), LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("Courses")
				.child(InstrCourseActivity.state.courID)
				.child("Quiz")
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						quizzes = new ArrayList<>();
						List<String> check = new ArrayList<>();
						Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
						while(itr.hasNext()) {
							String tmp = itr.next().getKey();
							quizzes.add(tmp);
							check.add((String)dataSnapshot.child(tmp).child("state").getValue());
						}
						viewList = view.findViewById(R.id.recyclerViewQuiz);
						LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
						viewList.setLayoutManager(linearLayoutManager);
						mAdapter = new QuizManiAdapter(view.getContext(), quizzes, check);
						viewList.setAdapter(mAdapter);
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
		upload_quiz = view.findViewById(R.id.upload_quiz);
		upload_quiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), NewQuizActivity.class);
				startActivity(intent);
			}
		});
		// Inflate the layout for this fragment
		return view;
	}
	
}
