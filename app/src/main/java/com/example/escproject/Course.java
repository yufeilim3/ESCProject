package com.example.escproject;

public class Course {
    private int ID;
    String name;
    int classID;
    List<Quiz> quizzes;
    List<Student> students;
    List<Instructor> instructors;

    public Course(int ID, String name, int classID, List<Student> students, List<Instructor> instructors) {
        this.ID = ID;
        this.name = name;
        this.classID = classID;
        this.students = students;
        this.instructors = instructors;
    }
}

