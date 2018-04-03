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
				databaseReference.child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						if(dataSnapshot.hasChild(courseID)&&dataSnapshot.child(courseID).hasChild(classID)) {
							if(CourseActivity.userType.equals("Student")) {
								databaseReference.child("Courses")
										.child(courseID)
										.child(classID)
										.child(CourseActivity.userType)
										.child(firebaseUser.getUid()).setValue(CourseActivity.currentUser);
								CourseActivity.currentUser.courses.add(new Course(courseID, courseName, classID));
							} else if(CourseActivity.userType.equals("Instructor")) {
								databaseReference.child("Courses")
										.child(courseID)
										.child(classID)
										.child(CourseActivity.userType)
										.child(firebaseUser.getUid()).setValue(CourseActivity.currentInstr);
								CourseActivity.currentInstr.courses.add(new Course(courseID, courseName, classID));
							}
							databaseReference.child("users")
									.child(CourseActivity.userType)
									.child(firebaseUser.getUid())
									.child("Course")
									.child(courseID)
									.setValue(new Course(courseID,courseName,classID));
							Toast.makeText(AddCourseActivity.this, "Add course successfully", Toast.LENGTH_LONG).show();
							Intent intent = new Intent(AddCourseActivity.this, CourseActivity.class);
							startActivity(intent);
							finish();
						} else Toast.makeText(AddCourseActivity.this, "No such class from this course", Toast.LENGTH_LONG).show();
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
