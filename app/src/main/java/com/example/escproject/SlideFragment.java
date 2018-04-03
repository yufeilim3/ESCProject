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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.escproject.CourseActivity.currentUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideFragment extends Fragment {
	private RecyclerView viewList;
	private SlideAdapter mAdapter;
	private List<onlineFile> slides;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	private View view;
	
	public SlideFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_slide, container, false);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this.getActivity(), LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("Courses")
				.child(MyCourseActivity.state.courID)
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
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
								startActivity(intent);
							}
						}));
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
		// Inflate the layout for this fragment
		return view;
	}
	
}
