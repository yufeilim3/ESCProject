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

public class QuestionAdapter  extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{
	List<Question> questions;
	private  int viewHolderCount = 0;
	Context parentContext;
	List<EditText> edit_answers;
	
	QuestionAdapter(Context context, List<Question> questions){
		this.parentContext = context;
		this.questions = questions;
		edit_answers = new ArrayList<>();
	}
	
	@Override
	public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		int layoutIDForListItem = R.layout.questionviewholder;
		LayoutInflater inflater = LayoutInflater.from(parentContext);
		boolean shouldAttachToParentImmediately = false;
		View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
		QuestionViewHolder ViewHolder = new QuestionViewHolder(view);
		viewHolderCount++;
		edit_answers.add(ViewHolder.answer);
		return ViewHolder;
	}
	
	@Override
	public void onBindViewHolder(QuestionViewHolder holder, int position) {
		holder.bind(position);
	}
	
	@Override
	public int getItemCount() {
		return questions.size();
	}
	
	class QuestionViewHolder extends RecyclerView.ViewHolder{
		TextView question;
		EditText answer;
		
		QuestionViewHolder(View v){
			super(v);
			question = v.findViewById(R.id.question);
			answer = v.findViewById(R.id.edit_answer);
			
		}
		
		public void bind(int position ){
			String text = (position+1)+". " +questions.get(position).question+"("+questions.get(position).point+"pts)";
			question.setText(text);
		}
	}
}
