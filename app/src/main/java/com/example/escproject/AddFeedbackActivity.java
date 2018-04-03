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

public class AddFeedbackActivity extends AppCompatActivity {
	static String state;
	private RecyclerView viewList;
	private FeedbackAdapter mAdapter;
	private List<String> feedbacks;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	EditText myFeedbacks;
	Button adding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_feedback);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
		Log.i("size",InstrViewSlideActivity.state.name+InstrViewSlideActivity.state.type);
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("Courses")
				.child(MyCourseActivity.state.courID)
				.child("Slide")
				.child(InstrViewSlideActivity.state.name+InstrViewSlideActivity.state.type)
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						feedbacks = new ArrayList<>();
						if(dataSnapshot.hasChild(state)) {
							Log.i("size","OK");
							Iterator<DataSnapshot> itr = dataSnapshot.child(state).getChildren().iterator();
							while(itr.hasNext()) {
								feedbacks.add((String)itr.next().getValue());
							}
						}
						Log.i("size",String.valueOf(feedbacks.size()));
						viewList = findViewById(R.id.recyclerViewFeedback);
						LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddFeedbackActivity.this);
						viewList.setLayoutManager(linearLayoutManager);
						mAdapter = new FeedbackAdapter(AddFeedbackActivity.this, feedbacks);
						viewList.setAdapter(mAdapter);
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
		adding = findViewById(R.id.btn_add);
		myFeedbacks = findViewById(R.id.edit_feedback);
		adding.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String text = myFeedbacks.getText().toString().trim();
				if(!text.equals("")&&text!=null) {
					databaseReference.child("Courses")
							.child(MyCourseActivity.state.courID)
							.child("Slide")
							.child(InstrViewSlideActivity.state.name+InstrViewSlideActivity.state.type)
							.child(state)
							.child(String.valueOf(mAdapter.getItemCount()+1))
							.setValue(text);
					myFeedbacks.setText(" ");
				}
			}
		});
	}
}
