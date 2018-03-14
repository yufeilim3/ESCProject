package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Instructor implements User {
    final String instrID;
    final String name;
    List<Course> courses;
    Course state;

    //initialize a instructor account
    Instructor(String ID, String name) {
        this.instrID = ID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    //load a instructor account from database
    Instructor(String ID, String name, List<Course> courses, Course state) {
        this.instrID = ID;
        this.name = name;
        this.courses = courses;
        this.state = state;
    }

    String getID() {
        return instrID;
    }

    String getName() {
        return name;
    }

    List<Course> getCourses() {
        return courses;
    }

    Course getState() {
        return state;
    }

    void setState(Course state) {
        this.state = state;
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        course.instructors.add(this);
    }

    void postQuiz(Quiz quiz, Course course) {
        for (Student s : course.getStudents()) {
            s.setQuizState(quiz);
        }
    }
}
