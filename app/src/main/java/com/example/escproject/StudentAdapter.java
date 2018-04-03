package com.example.escproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
	
	List<String> students;
	List<Double> scores;
	private  int viewHolderCount = 0;
	Context parentContext;
	
	StudentAdapter(Context context, List<String> students, List<Double> scores){
		this.parentContext = context;
		this.students = students;
		this.scores = scores;
	}
	
	@Override
	public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.studentviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		StudentViewHolder ViewHolder = new StudentViewHolder(view);
		viewHolderCount++;
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(StudentViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return students.size();
	}
	
	class StudentViewHolder extends RecyclerView.ViewHolder{
		TextView studentName;
		TextView score;
		
		StudentViewHolder(View v){
			super(v);
			studentName = v.findViewById(R.id.student_name);
			score = v.findViewById(R.id.student_score);
			
		}
		
		public void bind(int position ){
			studentName.setText(students.get(position));
			score.setText(String.valueOf(scores.get(position)));
		}
	}
}
