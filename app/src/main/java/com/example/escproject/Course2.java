package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Course2 {
    private String id;
    private String name;
    private List<String> students = new ArrayList<>();
    private List<String> instructors = new ArrayList<>();

    public Course2() {
    }

    public Course2(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getStudents() {
        return students;
    }

    public List<String> getInstructors() {
        return instructors;
    }

    public void addStudent(String id) {
        students.add(id);
    }

    public void addInstructor(String id) {
        instructors.add(id);
    }
}