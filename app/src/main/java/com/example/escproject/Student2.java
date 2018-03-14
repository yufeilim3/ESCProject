package com.example.escproject;

import java.util.HashMap;
import java.util.Map;

public class Student2 extends User2 {
    private Map<String, Double> courses = new HashMap<>();

    public Student2() {
        super();
    }

    public Student2(String id, String name) {
        super(id, name, 2);
    }

    public Map<String, Double> getCourses() {
        return courses;
    }

    public void addCourse(String id) {
        courses.put(id, 0.0);
    }

    public void updateGrade(String id, double grade) {
        courses.put(id, grade);
    }
}
