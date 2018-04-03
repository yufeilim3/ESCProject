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
public class InstrOtherFragment extends Fragment {
	private RecyclerView viewList;
	private SlideAdapter mAdapter;
	private List<onlineFile> files;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	private View view;
	Button upload_file;
	
	
	public InstrOtherFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_instr_other, container, false);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this.getActivity(), LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("Courses")
				.child(InstrCourseActivity.state.courID)
				.child("Other")
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
						files = new ArrayList<>();
						while(itr.hasNext()) {
							files.add(itr.next().getValue(onlineFile.class));
						}
						viewList = view.findViewById(R.id.recyclerViewOther);
						mAdapter = new SlideAdapter(view.getContext(), files);
						LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
						viewList.setLayoutManager(linearLayoutManager);
						mAdapter = new SlideAdapter(view.getContext(), files);
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
		upload_file = view.findViewById(R.id.upload_file);
		upload_file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			
			}
		});
		// Inflate the layout for this fragment
		return view;
	}
	
}
