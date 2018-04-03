package com.example.escproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
	List<String> quizzes;
	private  int viewHolderCount = 0;
	Context parentContext;
	
	QuizAdapter(Context context, List<String> quizzes){
		this.parentContext = context;
		this.quizzes = quizzes;
	}
	
	@Override
	public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.resourceviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		QuizViewHolder ViewHolder = new QuizViewHolder(view);
		viewHolderCount++;
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(QuizViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return quizzes.size();
	}
	
	class QuizViewHolder extends RecyclerView.ViewHolder{
		TextView quizName;
		
		QuizViewHolder(View v){
			super(v);
			quizName = v.findViewById(R.id.resource_name);
			
		}
		
		public void bind(int position ){
			String name = "quiz "+quizzes.get(position);
			quizName.setText(name);
		}
	}
}
