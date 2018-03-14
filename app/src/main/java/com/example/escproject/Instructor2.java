package com.example.escproject;

import java.util.ArrayList;
import java.util.List;

public class Instructor2 extends User2 {
    private List<String> courses = new ArrayList<>();

    public Instructor2() {
        super();
    }

    public Instructor2(String id, String name) {
        super(id, name, 1);
    }

    public List<String> getCourses() {
        return this.courses;
    }

    public void addCourse(String id) {
        courses.add(id);
    }
}