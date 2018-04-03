package com.example.escproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class CourseActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private RecyclerView courseList;
    private CourseAdapter mAdapter;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
    static Student currentUser;
    static Instructor currentInstr;
    Button addCourse;
    static String userType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        auth = FirebaseAuth.getInstance();
        
        if (auth.getCurrentUser()==null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
	    
        firebaseUser = auth.getCurrentUser();
	    databaseReference = FirebaseDatabase.getInstance().getReference();
	    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) {
			    String name = "";
			    String ID = "";
			    if(dataSnapshot.child("Student").hasChild(firebaseUser.getUid())) {
				    name = dataSnapshot.child("Student").child(firebaseUser.getUid()).child("name").getValue().toString();
				    ID = dataSnapshot.child("Student").child(firebaseUser.getUid()).child("studID").getValue().toString();
				    userType = "Student";
				    currentUser = new Student(ID, name);
				    if(dataSnapshot.child(userType).child(firebaseUser.getUid()).hasChild("Course")) {
				    	Iterator<DataSnapshot> itr = dataSnapshot.child(userType).child(firebaseUser.getUid()).child("Course").getChildren().iterator();
				    	while(itr.hasNext()) {
				    		currentUser.courses.add(itr.next().getValue(Course.class));
					    }
				    }
			    } else if(dataSnapshot.child("Instructor").hasChild(firebaseUser.getUid())) {
				    name = dataSnapshot.child("Instructor").child(firebaseUser.getUid()).child("name").getValue().toString();
				    ID = dataSnapshot.child("Instructor").child(firebaseUser.getUid()).child("instrID").getValue().toString();
				    userType = "Instructor";
				    currentInstr = new Instructor(ID, name);
				    if(dataSnapshot.child(userType).child(firebaseUser.getUid()).hasChild("Course")) {
					    Iterator<DataSnapshot> itr = dataSnapshot.child(userType).child(firebaseUser.getUid()).child("Course").getChildren().iterator();
					    while(itr.hasNext()) {
						    currentInstr.courses.add(itr.next().getValue(Course.class));
					    }
				    }
			    }
			    //Log.i("size: ", String.valueOf(currentInstr.courses.size()));
			    courseList = findViewById(R.id.recyclerViewAnime);
			    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CourseActivity.this);
			    courseList.setLayoutManager(linearLayoutManager);
			    if(userType.equals("Instructor")) mAdapter = new CourseAdapter(CourseActivity.this, currentInstr.courses);
			    else mAdapter = new CourseAdapter(CourseActivity.this, currentUser.courses);
			    courseList.setAdapter(mAdapter);
			    courseList.addOnItemTouchListener(new RecyclerItemClickListener(CourseActivity.this, new RecyclerItemClickListener.OnItemClickListener(){
				    @Override
				    public void onItemClick(View view, int position) {
				    	Intent intent;
				    	if(userType.equals("Instructor")) {
				    		intent = new Intent(CourseActivity.this, InstrCourseActivity.class);
				    		InstrCourseActivity.state = mAdapter.courses.get(position);
					    }
				    	else {
				    		intent = new Intent(CourseActivity.this, MyCourseActivity.class);
						    MyCourseActivity.state = mAdapter.courses.get(position);
					    }
					    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					    startActivity(intent);
					    //finish();
				    }
			    }));
		    }
		
		    @Override
		    public void onCancelled(DatabaseError databaseError) {
		    }
	    });
	    
        addCourse = (Button) findViewById(R.id.add);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, AddCourseActivity.class);
                startActivity(intent);
                //finish();
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
                Intent intent = new Intent(CourseActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}