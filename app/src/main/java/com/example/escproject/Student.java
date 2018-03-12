package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Student implements User{
    final int ID;
    final String name;
    List<Course> courses;
    Course state;
    Quiz quizState;
    List<List<String>> answers;
    List<Double> grades;

    //initialize a student account
    Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    //load a student account from database
    Student(int ID, String name, List<Course> courses, List<List<String>> answers, List<Double> grades) {
        this.ID = ID;
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

    int getID() {
        return ID;
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

    void answerQuiz(Quiz quiz){
        quiz.updateGrade(this);
        quizState = null;
    }
    
    void addFeedback(String slides, int page, String content) {
        Feedback f = new Feedback(slides, page, content, this);
    }

    String quizzesToString() {
        String output = "";
        for (Quiz q : state.quizzes) {
            output += "Quiz " + q.getID() + "\n";
        }
        return output;
    }
}
