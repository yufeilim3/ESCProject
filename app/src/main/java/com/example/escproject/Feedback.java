package com.example.escproject;

public class Feedback {
    String slides;
    int page;
    String content;
    Student student;
    
    public Feedback(String slides, int page, String content, Student student) {
        this.slides = slides;
        this.page = page;
        this.content = content;
        this.student = student;
    }
}
