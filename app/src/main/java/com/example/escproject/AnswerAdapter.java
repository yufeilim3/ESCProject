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

/**
 * Created by 94360 on 2018/4/2.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>{
	List<String> answers;
	List<Question> questions;
	private  int viewHolderCount = 0;
	Context parentContext;
	
	AnswerAdapter(Context context, List<Question> questions, List<String> answers){
		this.parentContext = context;
		this.questions = questions;
		this.answers = answers;
	}
	
	@Override
	public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.answerviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		AnswerViewHolder ViewHolder = new AnswerViewHolder(view);
		viewHolderCount++;
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(AnswerViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return questions.size();
	}
	
	class AnswerViewHolder extends RecyclerView.ViewHolder{
		TextView question;
		TextView correctAnswer;
		TextView myAnswer;
		
		AnswerViewHolder(View v){
			super(v);
			question = v.findViewById(R.id.question);
			myAnswer = v.findViewById(R.id.my_answer);
			correctAnswer = v.findViewById(R.id.correct_answer);
			
		}
		
		public void bind(int position ){
			String text = (position+1)+"." +questions.get(position).question+"("+questions.get(position).point+"pts)";
			question.setText(text);
			correctAnswer.setText("Correct Answer: "+questions.get(position).getAnswer());
			myAnswer.setText("My Answer: "+answers.get(position));
		}
	}
}
