package com.example.escproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 94360 on 2018/4/3.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>{
	List<String> feedbacks;
	private  int viewHolderCount = 0;
	Context parentContext;
	
	FeedbackAdapter(Context context, List<String> feedbacks){
		this.parentContext = context;
		this.feedbacks = feedbacks;
	}
	
	@Override
	public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.feedbackviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		FeedbackViewHolder ViewHolder = new FeedbackViewHolder(view);
		viewHolderCount++;
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(FeedbackViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return feedbacks.size();
	}
	
	class FeedbackViewHolder extends RecyclerView.ViewHolder{
		TextView feedback;
		
		FeedbackViewHolder(View v){
			super(v);
			feedback = v.findViewById(R.id.feedback);
			
		}
		
		public void bind(int position ){
			String name = feedbacks.get(position);
			feedback.setText(name);
		}
	}
}
