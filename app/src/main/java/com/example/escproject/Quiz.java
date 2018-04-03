package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    final String ID;
    Course course;
    List<Question> questions;
    
    Quiz(String ID) {
	    this.questions = new ArrayList<>();
        this.ID = ID;
    }
    
    Quiz(Course course, List<Question> questions, String ID) {
        this.course = course;
	    this.questions = questions;
        this.ID = ID;
    }

    Quiz(String ID, Course course) {
        this.ID = ID;
        this.course = course;
        questions = new ArrayList<>();
    }

    public String calculateGrade(List<String> answers) {
		double grade = 0;
		double totalPoint = 0;
		for(int i=0;i<answers.size();i++) {
			if(answers.get(i).equals(questions.get(i).getAnswer())) {
				grade += questions.get(i).point;
			}
			totalPoint += questions.get(i).point;
		}
		return grade+"/"+totalPoint;
    }
}

class Question {
    String question;
    private String answer;
    double point;
	
	public Question() {
	}
	
	public Question(String content, String answer, double points) {
        //this.name = name;
        this.question = content;
        this.point = points;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
	
}
