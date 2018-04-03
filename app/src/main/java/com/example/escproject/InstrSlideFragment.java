package com.example.escproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class InstrSlideFragment extends Fragment {
	private RecyclerView viewList;
	private SlideAdapter mAdapter;
	private List<onlineFile> slides;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	private View view;
	Button upload_slide;
	
	public InstrSlideFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_instr_slide, container, false);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this.getActivity(), LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("Courses")
				.child(InstrCourseActivity.state.courID)
				.child("Slide")
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
						slides = new ArrayList<>();
						while(itr.hasNext()) {
							slides.add(itr.next().getValue(onlineFile.class));
						}
						viewList = view.findViewById(R.id.recyclerViewSlide);
						mAdapter = new SlideAdapter(view.getContext(), slides);
						LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
						viewList.setLayoutManager(linearLayoutManager);
						mAdapter = new SlideAdapter(view.getContext(), slides);
						viewList.setAdapter(mAdapter);
						viewList.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener(){
							@Override
							public void onItemClick(View view, int position) {
								Intent intent = new Intent(view.getContext(), MyCourseActivity.class);
								startActivity(intent);
							}
						}));
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
		
		upload_slide = view.findViewById(R.id.upload_slide);
		upload_slide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			
			}
		});
		// Inflate the layout for this fragment
		return view;
	}
	
}
