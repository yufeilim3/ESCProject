package com.example.escproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
	
	QuestionUploadAdapter(Context context, List<Question> questions){
		this.parentContext = context;
		this.questions = questions;
	}
	
	@Override
	public QuestionUploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.questionuploadviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		QuestionUploadViewHolder ViewHolder = new QuestionUploadViewHolder(view);
		viewHolderCount++;
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
		
		public void bind(final int position ){
			String text = questions.get(position).question;
			String answertext = questions.get(position).answer;
			title.setText("Question "+(position+1)+": ");
			point.setText(String.valueOf(questions.get(position).point));
			question.setText(text);
			answer.setText(answertext);
			question.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				}
				
				@Override
				public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
					if(charSequence.length()!=0) {
						questions.get(position).question = question.getText().toString().trim();
					}
				}
				
				@Override
				public void afterTextChanged(Editable editable) {
				
				}
			});
			answer.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				}
				
				@Override
				public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
					if(charSequence.length()!=0) {
						questions.get(position).answer = answer.getText().toString().trim();
					}
				}
				
				@Override
				public void afterTextChanged(Editable editable) {
				
				}
			});
			point.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				}
				
				@Override
				public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
					if(charSequence.length()!=0) {
						questions.get(position).point = Double.parseDouble(point.getText().toString().trim());
					}
				}
				
				@Override
				public void afterTextChanged(Editable editable) {
				
				}
			});
		}
	}
}
