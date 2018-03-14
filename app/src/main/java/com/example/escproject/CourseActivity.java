package com.example.escproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CourseActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    TabHost tabs;
    Button adminCreateCourseButton, adminDeleteCourseButton, instrAddCourseButton,
            instrChangeCourseButton, instrPostQuizButton, stud1AddCourseButton, stud2AddCourseButton;
    TextView adminTextView, instrCurrentCourseTextView, stud1TextView, stud2TextView;
    EditText adminCourseEditText, instrCourseEditText, stud1CourseEditText, stud2CourseEditText;

    int courseCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_course);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_logout:
                        auth.signOut();
                        Toast.makeText(getApplicationContext(),"Logout Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CourseActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser()==null){
            Intent intent = new Intent(CourseActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        FirebaseUser user = auth.getCurrentUser();

        tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec tabSpec = tabs.newTabSpec("ADMIN");
        tabSpec.setContent(R.id.admin);
        tabSpec.setIndicator("ADMIN");
        tabs.addTab(tabSpec);
        tabSpec = tabs.newTabSpec("INSTR");
        tabSpec.setContent(R.id.instr);
        tabSpec.setIndicator("INSTR");
        tabs.addTab(tabSpec);
        tabSpec = tabs.newTabSpec("STUD_1");
        tabSpec.setContent(R.id.stud_1);
        tabSpec.setIndicator("STUD_1");
        tabs.addTab(tabSpec);
        tabSpec = tabs.newTabSpec("STUD_2");
        tabSpec.setContent(R.id.stud_2);
        tabSpec.setIndicator("STUD_2");
        tabs.addTab(tabSpec);

        adminCreateCourseButton = findViewById(R.id.adminCreateCourseButton);
        adminDeleteCourseButton = findViewById(R.id.adminDeleteCourseButton);
        instrAddCourseButton = findViewById(R.id.instrAddCourseButton);
        instrChangeCourseButton = findViewById(R.id.instrChangeCourseButton);
        instrPostQuizButton = findViewById(R.id.instrPostQuizButton);
        stud1AddCourseButton = findViewById(R.id.stud1AddCourseButton);
        stud2AddCourseButton = findViewById(R.id.stud2AddCourseButton);
        instrCurrentCourseTextView = findViewById(R.id.instrCurrentCourseTextView);
        adminCourseEditText = findViewById(R.id.adminCourseEditText);
        instrCourseEditText = findViewById(R.id.instrCourseEditText);
        stud1CourseEditText = findViewById(R.id.stud1CourseEditText);
        stud2CourseEditText = findViewById(R.id.stud2CourseEditText);
        adminTextView = findViewById(R.id.adminTextView);
        stud1TextView = findViewById(R.id.stud1TextView);
        stud2TextView = findViewById(R.id.stud2TextView);


        final Administrator administrator = new Administrator(0, "admin");
        final Instructor instructor = new Instructor(Integer.toString(1), "instr");
        final Student student1 = new Student(Integer.toString(2), "stud1");
        final Student student2 = new Student(Integer.toString(3), "stud2");

        adminCreateCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                administrator.createCourse(courseCounter, adminCourseEditText.getText().toString(),1);
                courseCounter++;
                adminCourseEditText.setText("");
                adminTextView.setText(administrator.coursesToString());
            }
        });

        adminDeleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                administrator.deleteCourse(new Course(0, adminCourseEditText.getText().toString(),1));
                adminCourseEditText.setText("");
                adminTextView.setText(administrator.coursesToString());
            }
        });

        instrAddCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructor.addCourse(administrator.findCourse(instrCourseEditText.getText().toString()));
                instrCourseEditText.setText("");
            }
        });

        instrChangeCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructor.setState(administrator.findCourse(instrCourseEditText.getText().toString()));
                String courseText = "Current course: " + instructor.getState().name;
                instrCurrentCourseTextView.setText(courseText);
                instrCourseEditText.setText("");
            }
        });

        instrPostQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quizName = instructor.getState().name + " Quiz";
                instructor.postQuiz(new Quiz(0, instructor.getState()), instructor.getState());
                stud1TextView.setText(student1.quizzesToString());
                stud2TextView.setText(student2.quizzesToString());
            }
        });

        stud1AddCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student1.addCourse(administrator.findCourse(stud1CourseEditText.getText().toString()));
                stud1CourseEditText.setText("");
            }
        });

        stud2AddCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student2.addCourse(administrator.findCourse(stud2CourseEditText.getText().toString()));
                stud2CourseEditText.setText("");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (actionBarDrawerToggle.onOptionsItemSelected(menuItem)){
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}