package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Course {
    final int ID;
    final String name;
    final int classID;
    List<Quiz> quizzes;
    List<Student> students;
    List<Instructor> instructors;

    Course(int ID, String name, int classID, List<Student> students, List<Instructor> instructors) {
        this.ID = ID;
        this.name = name;
        this.classID = classID;
        this.students = students;
        this.instructors = instructors;
    }

    Course(int ID, String name, int classID) {
        this.ID = ID;
        this.name = name;
        this.classID = ID;
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
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
}

