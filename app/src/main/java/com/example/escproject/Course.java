package com.example.escproject;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> parent of f27357f... stub
=======
>>>>>>> parent of f27357f... stub
public class Course {
<<<<<<< HEAD
    private int ID;
    String name;
    int classID;
=======
import java.util.ArrayList;
import java.util.List;

public class Course {
    final int ID;
    final String name;
    final int classID;
>>>>>>> yuanzhi
    List<Quiz> quizzes;
    List<Student> students;
    List<Instructor> instructors;

<<<<<<< HEAD
    public Course(int ID, String name, int classID, List<Student> students, List<Instructor> instructors) {
=======
    Course(int ID, String name, int classID, List<Student> students, List<Instructor> instructors) {
>>>>>>> yuanzhi
        this.ID = ID;
        this.name = name;
        this.classID = classID;
        this.students = students;
        this.instructors = instructors;
    }
<<<<<<< HEAD
=======

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
>>>>>>> yuanzhi
=======



>>>>>>> parent of 8456c2d... Merge branch 'yufei' of https://github.com/yufeilim3/ESCProject into yufei
}

