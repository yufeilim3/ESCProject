package com.example.escproject;

import java.util.List;

public class Student implements User{
    int ID;
    String name;
    List<Course> courses;
    Course state;
    List<List<String>> answers;
    List<Double> grades;

    public Course getState() {
        return state;
    }

    public void setState(Course state) {
        this.state = state;
    }

    public List<List<String>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<List<String>> answers) {
        this.answers = answers;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        course.students.add(this);
    }

    public void answerQuiz(Quiz quiz){

    }
    
    public void addFeedback(String slides, int page, String content) {
        Feedback f = new Feedback(slides, page, content, this);
    }
}
