package com.example.escproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    List<Course> courses;
    private static int viewHolderCount = 0;
    Context parentContext;

    CourseAdapter(Context context, List<Course> courses){
        this.parentContext = context;
        this.courses = courses;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIDForListItem = R.layout.courseviewholder;
        LayoutInflater inflater = LayoutInflater.from(parentContext);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
        CourseViewHolder CourseViewHolder = new CourseViewHolder(view);
        viewHolderCount++;
        return CourseViewHolder;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView courseName;
        TextView courseId;
        
        CourseViewHolder(View v){
            super(v);
            courseName = (TextView)v.findViewById(R.id.course_name);
            courseId = (TextView)v.findViewById(R.id.course_id);
            
        }
	    
        public void bind(int position ){
            String name = courses.get(position).getName();
            String id = courses.get(position).getCourID();
            courseName.setText(name);
            courseId.setText(id);
        }
    }
}
