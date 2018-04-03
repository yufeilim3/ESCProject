package com.example.escproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class InstrViewSlideActivity extends AppCompatActivity {
	static onlineFile state;
	Button download;
	Button questions;
	Button feedbacks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instr_view_slide);
		
		download = findViewById(R.id.btn_view);
		questions = findViewById(R.id.btn_question);
		feedbacks = findViewById(R.id.btn_feedback);
		
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			
			}
		});
		questions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent;
				if(CourseActivity.userType.equals("Instructor")) {
					intent = new Intent(view.getContext(), ViewFeedbackActivity.class);
					ViewFeedbackActivity.state = "question";
				}
				else {
					intent = new Intent(view.getContext(), AddFeedbackActivity.class);
					AddFeedbackActivity.state = "question";
				}
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		feedbacks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent;
				if(CourseActivity.userType.equals("Instructor")) {
					intent = new Intent(view.getContext(), ViewFeedbackActivity.class);
					ViewFeedbackActivity.state = "feedback";
				}
				else {
					intent = new Intent(view.getContext(), AddFeedbackActivity.class);
					AddFeedbackActivity.state = "feedback";
				}
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
	}
}
