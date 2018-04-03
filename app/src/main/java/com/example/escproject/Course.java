package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String courID;
    public String name;
    public String classID;
    List<Quiz> quizzes;
    List<Student> students;
    List<Instructor> instructors;
	
	public Course() {
	}
	
	Course(String courID, String name, String classID, List<Student> students, List<Instructor> instructors, List<Quiz> quizzes) {
        this.courID = courID;
        this.name = name;
        this.classID = classID;
        this.students = students;
        this.instructors = instructors;
        this.quizzes = quizzes;
    }

    Course(String courID, String name, String classID) {
        this.courID = courID;
        this.name = name;
        this.classID = classID;
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.quizzes = new ArrayList<>();
    }

    void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    List<Instructor> getInstructors() {
        return instructors;
    }

    void addStudent(Student student) {
        students.add(student);
    }

    List<Student> getStudents() {
        return students;
    }

    public String getCourID() {
        return courID;
    }

    public String getName() {
        return name;
    }
}
