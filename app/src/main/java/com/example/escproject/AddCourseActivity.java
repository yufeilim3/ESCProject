package com.example.escproject;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourseActivity extends AppCompatActivity {
	private FirebaseAuth auth;
	private TextView courseName, courseID, classID;
	private EditText editCourseName, editCourseID, editClassID;
	private Button add;
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
		databaseReference = FirebaseDatabase.getInstance().getReference();
		
		courseName = findViewById(R.id.course_name);
		courseID = findViewById(R.id.course_id);
		classID = findViewById(R.id.class_id);
		editClassID = findViewById(R.id.edit_class_id);
		editCourseID = findViewById(R.id.edit_course_id);
		editCourseName = findViewById(R.id.edit_course_name);
		add = findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FirebaseUser firebaseUser = auth.getCurrentUser();
				DatabaseReference database =
						databaseReference.child("courses")
								.child(editCourseID.getText().toString())
								.child(editClassID.getText().toString())
								.getDatabase().getReference();
				database.getKey();
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
