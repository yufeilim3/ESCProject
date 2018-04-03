package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    String ID;
    String state;
    Course course;
    List<Question> questions;
    
    Quiz(String ID, String state) {
	    this.questions = new ArrayList<>();
        this.ID = ID;
        this.state = state;
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
			if(answers.get(i).equals(questions.get(i).answer)) {
				grade += questions.get(i).point;
			}
			totalPoint += questions.get(i).point;
		}
		return grade+"/"+totalPoint;
    }
}

class Question {
    public String question;
    public String answer;
    public double point;
	
	public Question() {
	}
	
	public Question(String content, String answer, double points) {
        //this.name = name;
        this.question = content;
        this.point = points;
        this.answer = answer;
    }
	
}
