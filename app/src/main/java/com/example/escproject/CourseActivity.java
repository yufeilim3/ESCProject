package com.example.escproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CourseActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private RecyclerView courseList;
    private CourseAdapter mAdapter;
    Student currentUser;
    Button addCourse;

    int courseCounter = 0;

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
        FirebaseUser user = auth.getCurrentUser();
        currentUser = new Student(" ","");

        courseList = (RecyclerView) findViewById(R.id.recyclerViewAnime);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        courseList.setLayoutManager(linearLayoutManager);
        mAdapter = new CourseAdapter(this, currentUser.courses, this);
        courseList.setAdapter(mAdapter);

        addCourse = (Button) findViewById(R.id.add);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, AddCourseActivity.class);
                startActivity(intent);
                finish();
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