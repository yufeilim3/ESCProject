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
public class InstrModifyQuizFragment extends Fragment {
	private FirebaseAuth auth;
	private RecyclerView questionList;
	private QuestionUploadAdapter mAdapter;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	Button submitButton;
	
	public InstrModifyQuizFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_instr_modify_quiz, container, false);
		
		auth = FirebaseAuth.getInstance();
		if (auth.getCurrentUser()==null){
			Intent intent = new Intent(view.getContext(), LoginActivity.class);
			startActivity(intent);
		}
		
		firebaseUser = auth.getCurrentUser();
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				List<Question> questions = new ArrayList<>();
				Iterator<DataSnapshot> itr = dataSnapshot.child("Courses")
						.child(InstrCourseActivity.state.courID)
						.child("Quiz")
						.child(InstrQuizActivity.state.ID).getChildren().iterator();
				while(itr.hasNext()) {
					try {
						Question tmp = itr.next().getValue(Question.class);
						questions.add(tmp);
					} catch (Exception ex) {
					
					}
				}
				InstrQuizActivity.state.questions = questions;
				questionList = view.findViewById(R.id.recyclerViewQuestion);
				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
				questionList.setLayoutManager(linearLayoutManager);
				mAdapter = new QuestionUploadAdapter(view.getContext(), questions);
				questionList.setAdapter(mAdapter);
				
				submitButton = view.findViewById(R.id.btn_submit);
				submitButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						double totalPoint = 0;
						DatabaseReference quizRef = databaseReference.child("Courses")
								.child(InstrCourseActivity.state.courID)
								.child("Quiz")
								.child(InstrQuizActivity.state.ID);
						for(int i=0;i<InstrQuizActivity.state.questions.size();i++) {
							quizRef.child(String.valueOf(i+1)).setValue(InstrQuizActivity.state.questions.get(i));
							totalPoint += InstrQuizActivity.state.questions.get(i).point;
						}
						quizRef.child("grade").setValue(totalPoint);
						Intent intent = new Intent(view.getContext(), InstrCourseActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						startActivity(intent);
					}
				});
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
		return view;
	}
	
}
