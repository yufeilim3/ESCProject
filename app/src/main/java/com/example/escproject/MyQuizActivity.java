package com.example.escproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class MyQuizActivity extends AppCompatActivity {
	static Quiz state;
	private FirebaseAuth auth;
	private RecyclerView questionList;
	private QuestionAdapter mAdapter;
	private AnswerAdapter myAnswerAdapter;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	Button submitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_quiz);
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
				List<Question> questions = new ArrayList<>();
				Iterator<DataSnapshot> itr = dataSnapshot.child("Courses")
						.child(MyCourseActivity.state.courID)
						.child("Quiz")
						.child(state.ID).getChildren().iterator();
				while(itr.hasNext()) {
					try {
						Question tmp = itr.next().getValue(Question.class);
						questions.add(tmp);
					} catch (Exception ex) {
					
					}
				}
				state.questions = questions;
				questionList = findViewById(R.id.recyclerViewQuestion);
				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyQuizActivity.this);
				questionList.setLayoutManager(linearLayoutManager);
				
				DataSnapshot quizSnapShot = dataSnapshot.child("users")
						.child(CourseActivity.userType)
						.child(firebaseUser.getUid())
						.child("Course")
						.child(MyCourseActivity.state.courID);
				if(quizSnapShot.hasChild("Quiz")&&quizSnapShot.child("Quiz").hasChild(state.ID)) {
					List<String> answers = new ArrayList<>();
					for(int i=0;i<questions.size();i++) {
						answers.add((String)quizSnapShot.child("Quiz").child(state.ID).child(String.valueOf(i+1)).getValue());
					}
					String point = (String)quizSnapShot.child("Quiz").child(state.ID).child("grade").getValue();
					myAnswerAdapter = new AnswerAdapter(MyQuizActivity.this, questions, answers);
					questionList.setAdapter(myAnswerAdapter);
					submitButton = findViewById(R.id.btn_submit);
					submitButton.setText(point);
					submitButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Intent intent = new Intent(MyQuizActivity.this, MyCourseActivity.class);
							startActivity(intent);
						}
					});
				} else {
					mAdapter = new QuestionAdapter(MyQuizActivity.this, questions);
					questionList.setAdapter(mAdapter);
					
					submitButton = findViewById(R.id.btn_submit);
					submitButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							List<String> answers = new ArrayList<>();
							for(int i=0;i<mAdapter.getItemCount();i++) {
								answers.add(mAdapter.edit_answers.get(i).getText().toString().trim());
							}
							String point = state.calculateGrade(answers);
							DatabaseReference quizRef = databaseReference.child("users")
									.child(CourseActivity.userType)
									.child(firebaseUser.getUid())
									.child("Course")
									.child(MyCourseActivity.state.courID)
									.child("Quiz")
									.child(state.ID);
							quizRef.child("grade").setValue(point);
							for(int i=0;i<answers.size();i++) {
								quizRef.child(String.valueOf(i+1)).setValue(answers.get(i));
							}
							
							Intent intent = new Intent(MyQuizActivity.this, MyCourseActivity.class);
							startActivity(intent);
						}
					});
				}
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}
}
