package com.example.escproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

public class NewQuizActivity extends AppCompatActivity {
	static Quiz state;
	private FirebaseAuth auth;
	private RecyclerView questionList;
	private QuestionUploadAdapter mAdapter;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	Button submitButton;
	Button addQuestion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_quiz);
		
		auth = FirebaseAuth.getInstance();
		if (auth.getCurrentUser()==null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
		firebaseUser = auth.getCurrentUser();
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Iterator<DataSnapshot> itr = dataSnapshot.child("Courses")
						.child(InstrCourseActivity.state.courID)
						.child("Quiz").getChildren().iterator();
				int max = 0;
				while(itr.hasNext()) {
					int temp = Integer.parseInt(itr.next().getKey());
					if(temp>max) max = temp;
				}
				max++;
				final int newQuiz = max;
				if(state == null) state = new Quiz(String.valueOf(newQuiz), "closed");
				state.questions.add(new Question("", "", 0));
				questionList = findViewById(R.id.recyclerViewQuestion);
				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewQuizActivity.this);
				questionList.setLayoutManager(linearLayoutManager);
				mAdapter = new QuestionUploadAdapter(NewQuizActivity.this, state.questions);
				questionList.setAdapter(mAdapter);
				
				submitButton = findViewById(R.id.btn_submit);
				submitButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						double totalPoint = 0;
						DatabaseReference quizRef = databaseReference.child("Courses")
								.child(InstrCourseActivity.state.courID)
								.child("Quiz")
								.child(String.valueOf(newQuiz));
						for(int i=0;i<state.questions.size();i++) {
							quizRef.child(String.valueOf(i+1)).setValue(state.questions.get(i));
							totalPoint += state.questions.get(i).point;
						}
						quizRef.child("grade").setValue(totalPoint);
						quizRef.child("state").setValue("closed");
						Intent intent = new Intent(NewQuizActivity.this, InstrCourseActivity.class);
						startActivity(intent);
					}
				});
				
				addQuestion = findViewById(R.id.btn_addQuestion);
				addQuestion.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						state.questions.add(new Question("", "", 0));
						mAdapter.notifyDataSetChanged();
					}
				});
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}
}
