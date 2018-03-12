package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Instructor implements User{
    final int ID;
    final String name;
    List<Course> courses;
    Course state;

    //initialize a instructor account
    Instructor(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    //load a instructor account from database
    Instructor(int ID, String name, List<Course> courses, Course state) {
        this.ID = ID;
        this.name = name;
        this.courses = courses;
        this.state = state;
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
