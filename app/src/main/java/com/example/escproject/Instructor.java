package com.example.escproject;

import java.util.List;

public class Instructor implements User{
    int ID;
    String name;
    List<Course> courses;
    Course state;

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

    public Course getState() {
        return state;
    }

    public void setState(Course state) {
        this.state = state;
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void postQuiz(Quiz quiz, List<Student> studentList){

    }
}
