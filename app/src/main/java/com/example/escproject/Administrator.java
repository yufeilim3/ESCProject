package com.example.escproject;

import java.util.ArrayList;
import java.util.List;


public class Administrator {
    final int id;
    final String name;
    private List<Course> courses = new ArrayList<>();

    Administrator(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Administrator(int id, String name, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    void createCourse(int ID, String name, int classID, List<Instructor> instructors, List<Student> students) {
        Course newCourse = new Course(ID, name, classID);
        for (Instructor i : instructors) {
            newCourse.addInstructor(i);
        }
        for (Student s : students) {
            newCourse.addStudent(s);
        }
        courses.add(newCourse);
    }

    List<Course> getCourses() {
        return courses;
    }

    String coursesToString() {
        String output = "";
        for (Course c : courses) {
            output += c.name + "\n";
        }
        return output;
    }

    void createCourse(int ID, String name, int classID) {
        courses.add(new Course(ID, name, classID));
    }

    void deleteCourse(Course course) {
        Course temp = null;
        for (Course c : courses) {
            if (c.name.equals(course.name)) {
                temp = c;
                break;
            }
        }
        if (temp != null) {
            courses.remove(temp);
        }
    }

    Course findCourse(String name) {
        for (Course c : courses) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
