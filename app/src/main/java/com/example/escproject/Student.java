package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Student implements User{
    public String studID;
    public String name;
    public String id;
    List<Course> courses;
    Course state;
    Quiz quizState;
    List<List<String>> answers;
    List<Double> grades;
    
    public Student() {
    }
    
    public Student(String studID, String name, String id) {
        this.studID = studID;
        this.name = name;
        this.id = id;
    }
    
    //initialize a student account
    Student(String ID, String name) {
        this.studID = ID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    //load a student account from database
    Student(String ID, String name, List<Course> courses, List<List<String>> answers, List<Double> grades) {
        this.studID = ID;
        this.name = name;
        this.courses = courses;
        this.answers = answers;
        this.grades = grades;
    }

    Quiz getQuizState() {
        return quizState;
    }

    void setQuizState(Quiz quizState) {
        this.quizState = quizState;
    }

    Course getState() {
        return state;
    }

    void setState(Course state) {
        this.state = state;
    }

    List<List<String>> getAnswers() {
        return answers;
    }

    List<Double> getGrades() {
        return grades;
    }

    String getID() {
        return studID;
    }

    String getName() {
        return name;
    }

    List<Course> getCourses() {
        return courses;
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        course.students.add(this);
    }

    void addFeedback(String slides, int page, String content) {
        Feedback f = new Feedback(slides, page, content, this);
    }

    String quizzesToString() {
        String output = "";
        for (Quiz q : state.quizzes) {
            output += "Quiz " + q.ID + "\n";
        }
        return output;
    }
}