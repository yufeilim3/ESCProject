package com.example.escproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class InstrQuizActivity extends AppCompatActivity {
	static Quiz state;
	private FirebaseAuth auth;
	private RecyclerView questionList;
	private QuestionUploadAdapter mAdapter;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	Button submitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instr_quiz);
		
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
						.child(InstrCourseActivity.state.courID)
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
				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstrQuizActivity.this);
				questionList.setLayoutManager(linearLayoutManager);
				mAdapter = new QuestionUploadAdapter(InstrQuizActivity.this, questions);
				questionList.setAdapter(mAdapter);
				
				submitButton = findViewById(R.id.btn_submit);
				submitButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						List<Question> questions = new ArrayList<>();
						double totalPoint = 0;
						for(int i=0;i<mAdapter.getItemCount();i++) {
							String content = mAdapter.edit_questions.get(i).getText().toString().trim();
							String answer = mAdapter.edit_answers.get(i).getText().toString().trim();
							double point = Double.parseDouble(mAdapter.edit_points.get(i).getText().toString().trim());
							Question tmp = new Question(content, answer, point);
							questions.add(tmp);
							totalPoint += point;
						}
						DatabaseReference quizRef = databaseReference.child("Courses")
								.child(InstrCourseActivity.state.courID)
								.child("Quiz")
								.child(state.ID);
						quizRef.child("grade").setValue(totalPoint);
						for(int i=0;i<questions.size();i++) {
							quizRef.child(String.valueOf(i+1)).setValue(questions.get(i));
						}
						
						Intent intent = new Intent(InstrQuizActivity.this, InstrCourseActivity.class);
						startActivity(intent);
					}
				});
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}
}
