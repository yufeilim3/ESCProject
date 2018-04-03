package com.example.escproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{
	List<onlineFile> slides;
	private  int viewHolderCount = 0;
	Context parentContext;
	
	SlideAdapter(Context context, List<onlineFile> slides){
		this.parentContext = context;
		this.slides = slides;
	}
	
	@Override
	public SlideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.resourceviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		SlideViewHolder ViewHolder = new SlideViewHolder(view);
		viewHolderCount++;
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(SlideViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return slides.size();
	}
	
	class SlideViewHolder extends RecyclerView.ViewHolder{
		TextView slideName;
		
		SlideViewHolder(View v){
			super(v);
			slideName = v.findViewById(R.id.resource_name);
			
		}
		
		public void bind(int position ){
			String name = slides.get(position).name+"."+slides.get(position).type;
			slideName.setText(name);
		}
	}
}
