package com.example.escproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by 94360 on 2018/4/3.
 */

public class QuizManiAdapter extends RecyclerView.Adapter<QuizManiAdapter.QuizManiViewHolder> {
	List<String> quizzes;
	private  int viewHolderCount = 0;
	Context parentContext;
	List<String> check;
	
	QuizManiAdapter(Context context, List<String> quizzes, List<String> check){
		this.parentContext = context;
		this.quizzes = quizzes;
		this.check = check;
	}
	
	@Override
	public QuizManiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.quizmaniviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		QuizManiViewHolder ViewHolder = new QuizManiViewHolder(view);
		viewHolderCount++;
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(QuizManiViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return quizzes.size();
	}
	
	class QuizManiViewHolder extends RecyclerView.ViewHolder{
		TextView quizName;
		Switch openning;
		
		QuizManiViewHolder(View v){
			super(v);
			quizName = v.findViewById(R.id.quiz_name);
			openning = v.findViewById(R.id.close_open);
		}
		
		public void bind(final int position ){
			final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Courses")
					.child(InstrCourseActivity.state.courID)
					.child("Quiz")
					.child(quizzes.get(position))
					.child("state");
			String name = "quiz "+quizzes.get(position);
			
			quizName.setText(name);
			quizName.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(view.getContext(), InstrQuizActivity.class);
					InstrQuizActivity.state = new Quiz(quizzes.get(position), MyCourseActivity.state);
					parentContext.startActivity(intent);
				}
			});
			ref.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					String check = (String)dataSnapshot.getValue();
					if(check.equals("open")!=openning.isChecked()) openning.setChecked(!openning.isChecked());
				}
				
				@Override
				public void onCancelled(DatabaseError databaseError) {
				
				}
			});
			openning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					if(b) ref.setValue("open");
					else ref.setValue("closed");
				}
			});
		}
	}
	
}
