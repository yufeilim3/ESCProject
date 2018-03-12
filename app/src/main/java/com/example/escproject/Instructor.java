package com.example.escproject;

<<<<<<< HEAD
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
=======
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
>>>>>>> yuanzhi
        this.state = state;
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        course.instructors.add(this);
    }

<<<<<<< HEAD
    public void postQuiz(Quiz quiz, List<Student> studentList){

=======
    void postQuiz(Quiz quiz, Course course) {
        for (Student s : course.getStudents()) {
            s.setQuizState(quiz);
        }
>>>>>>> yuanzhi
    }
}
