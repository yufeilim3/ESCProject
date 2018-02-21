package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int ID;
    Course course;
    List<Question> questions;
    //List<Answer> answers;

    public Quiz(String name, Course course, List<String> questions, List<String> answers, List<Double> points, int ID) {
        this.course = course;
        parseQuestions(questions, answers, points);
        this.ID = ID;
    }

    private void parseQuestions(List<String> questions, List<String> answers, List<Double> points) {
        this.questions = new ArrayList<>();
        int amount = questions.size();
        for(int i=0;i<amount;i++) {
            this.questions.add(new Question(questions.get(i), answers.get(i), points.get(i), i));
        }
    }

    public void calculateGrade(Student student) {
        List<String> answer = student.answers.get(student.answers.size()-1);
        double grade = 0;
        for(int i=0;i<questions.size();i++) {
            if(answer.get(i).equals(questions.get(i).getAnswer())) grade += questions.get(i).points;
        }
        student.grades.add(grade);
    }

    public int getID() {
        return ID;
    }
}

class Question {
    private int ID;
    String content;
    private String answer;
    double points;

    public Question(String content, String answer, double points, int ID) {
        //this.name = name;
        this.content = content;
        this.points = points;
        this.ID = ID;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public int getID() {
        return ID;
    }
}
