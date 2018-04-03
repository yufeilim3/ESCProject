package com.example.escproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class InstrCheckQuizFragment extends Fragment {
	private RecyclerView viewList;
	private StudentAdapter mAdapter;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	private View view;
	
	public InstrCheckQuizFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_instr_check_quiz, container, false);
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this.getActivity(), LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						Iterator<DataSnapshot> students = dataSnapshot.child("Courses")
								.child(InstrCourseActivity.state.courID)
								.child(InstrCourseActivity.state.classID)
								.child("Student").getChildren().iterator();
						List<String> studentID = new ArrayList<>();
						List<String> studentList = new ArrayList<>();
						List<Double> scoreList = new ArrayList<>();
						while(students.hasNext()) {
							studentID.add(students.next().getKey());
						}
						for(int i=0;i<studentID.size();i++) {
							DataSnapshot student = dataSnapshot.child("users").child("Student").child(studentID.get(i));
							try {
								String score = (String)student.child("Course")
										.child(InstrCourseActivity.state.courID)
										.child("Quiz")
										.child(InstrQuizActivity.state.ID)
										.child("grade").getValue();
								double grade = Double.parseDouble(score.split("/")[0]);
								studentList.add((String)student.child("name").getValue());
								scoreList.add(grade);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						TextView title = view.findViewById(R.id.title);
						if(studentList.size()==0) {
							title.setText("No Students finished quiz !!!");
						} else title.setText("Top Students in this Quiz: ");
						viewList = view.findViewById(R.id.recyclerViewStudent);
						LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
						viewList.setLayoutManager(linearLayoutManager);
						mAdapter = new StudentAdapter(view.getContext(), studentList, scoreList);
						viewList.setAdapter(mAdapter);
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
		// Inflate the layout for this fragment
		return view;
	}
	
}
