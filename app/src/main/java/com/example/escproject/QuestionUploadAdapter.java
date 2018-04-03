package com.example.escproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionUploadAdapter extends RecyclerView.Adapter<QuestionUploadAdapter.QuestionUploadViewHolder>{
	List<Question> questions;
	private  int viewHolderCount = 0;
	Context parentContext;
	List<EditText> edit_answers;
	List<EditText> edit_questions;
	List<EditText> edit_points;
	
	QuestionUploadAdapter(Context context, List<Question> questions){
		this.parentContext = context;
		this.questions = questions;
		edit_answers = new ArrayList<>();
		edit_points = new ArrayList<>();
		edit_questions = new ArrayList<>();
	}
	
	@Override
	public QuestionUploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.questionuploadviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		QuestionUploadViewHolder ViewHolder = new QuestionUploadViewHolder(view);
		viewHolderCount++;
		edit_answers.add(ViewHolder.answer);
		edit_questions.add(ViewHolder.question);
		edit_points.add(ViewHolder.point);
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(QuestionUploadViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return questions.size();
	}
	
	class QuestionUploadViewHolder extends RecyclerView.ViewHolder{
		EditText question;
		EditText answer;
		EditText point;
		TextView title;
		
		QuestionUploadViewHolder(View v){
			super(v);
			question = v.findViewById(R.id.edit_question);
			answer = v.findViewById(R.id.edit_answer);
			point = v.findViewById(R.id.edit_point);
			title = v.findViewById(R.id.title);
		}
		
		public void bind(int position ){
			String text = questions.get(position).question;
			String answer = questions.get(position).getAnswer();
			title.setText("Question "+(position+1)+": ");
			point.setText(String.valueOf(questions.get(position).point));
			question.setText(text);
			this.answer.setText(answer);
		}
	}
}
