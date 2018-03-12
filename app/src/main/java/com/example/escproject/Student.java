package com.example.escproject;

<<<<<<< HEAD
import java.util.List;

public class Student implements User{
    int ID;
    String name;
    List<Course> courses;
    Course state;
    List<List<String>> answers;
    List<Double> grades;

    public Course getState() {
        return state;
    }

    public void setState(Course state) {
        this.state = state;
    }

    public List<List<String>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<List<String>> answers) {
        this.answers = answers;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

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

=======
import java.util.ArrayList;
import java.util.List;

public class Student implements User{
    final int ID;
    final String name;
    List<Course> courses;
    Course state;
    Quiz quizState;
    List<List<String>> answers;
    List<Double> grades;

    //initialize a student account
    Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    //load a student account from database
    Student(int ID, String name, List<Course> courses, List<List<String>> answers, List<Double> grades) {
        this.ID = ID;
        this.name = name;
        this.courses = courses;
        this.answers = answers;
        this.grades = grades;
    }

    Quiz getQuizState() {
        return quizState;
    }

    void setQuizState(Quiz quizState) {
        this.quizState = quizState;
    }

    Course getState() {
        return state;
    }

    void setState(Course state) {
        this.state = state;
    }

    List<List<String>> getAnswers() {
        return answers;
    }

    List<Double> getGrades() {
        return grades;
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

>>>>>>> yuanzhi
    @Override
    public void addCourse(Course course) {
        courses.add(course);
        course.students.add(this);
    }

<<<<<<< HEAD
    public void answerQuiz(Quiz quiz){

    }
    
    public void addFeedback(String slides, int page, String content) {
        Feedback f = new Feedback(slides, page, content, this);
    }
=======
    void answerQuiz(Quiz quiz){
        quiz.updateGrade(this);
        quizState = null;
    }
    
    void addFeedback(String slides, int page, String content) {
        Feedback f = new Feedback(slides, page, content, this);
    }

    String quizzesToString() {
        String output = "";
        for (Quiz q : state.quizzes) {
            output += "Quiz " + q.getID() + "\n";
        }
        return output;
    }
>>>>>>> yuanzhi
}
