package com.example.escproject;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseActivity extends AppCompatActivity {
	private FirebaseAuth auth;
	TextInputEditText textCourseName;
	TextInputEditText textCourseID;
	TextInputEditText textClassID;
	Button addCourse;
	private DatabaseReference databaseReference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_course);
		
		auth = FirebaseAuth.getInstance();
		if (auth.getCurrentUser()==null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		final FirebaseUser firebaseUser = auth.getCurrentUser();
		databaseReference = FirebaseDatabase.getInstance().getReference();

		textCourseName =findViewById(R.id.input_courseName);
		textCourseID = findViewById(R.id.input_courseID);
		textClassID = findViewById(R.id.input_classID);
		addCourse = findViewById(R.id.btn_addCourse);
		addCourse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final String courseName = textCourseName.getText().toString();
				final String courseID = textCourseID.getText().toString();
				final String classID = textClassID.getText().toString();
				databaseReference = FirebaseDatabase.getInstance().getReference();
				databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
                        String studName = dataSnapshot.child("Student").child(firebaseUser.getUid()).child("name").getValue().toString();
                        String instrName = dataSnapshot.child("Instructor").child(firebaseUser.getUid()).child("name").getValue().toString();
						if (dataSnapshot.child("Student").hasChild(firebaseUser.getUid())){
							databaseReference.child("users").child("Student").child(firebaseUser.getUid()).child("Course").child(courseName).setValue(new Course(courseID,courseName,classID));
							databaseReference.child("Courses").child(courseName).child(firebaseUser.getUid()).setValue(new Student(firebaseUser.getUid(),studName));
						}
						else{
							databaseReference.child("users").child("Instructor").child(firebaseUser.getUid()).child("Course").child(courseName).setValue(new Course(courseID,courseName,classID));
                            databaseReference.child("Courses").child(courseName).child(firebaseUser.getUid()).setValue(new Instructor(firebaseUser.getUid(),instrName));
						}
					}

					@Override
					public void onCancelled(DatabaseError databaseError) {
					}
				});
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_course, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
			case R.id.logout:
				auth.signOut();
				Toast.makeText(getApplicationContext(),"Logout Successful", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(AddCourseActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
				break;
		}
		return true;
	}
}
