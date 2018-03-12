<<<<<<< HEAD
package com.example.escproject;
=======
<<<<<<< HEAD
package com.example.a94360.smartinteraction;
=======
package com.example.escproject;
>>>>>>> 3663fb360364227b4436b150fdec5a468fa8f43e
>>>>>>> yuanzhi

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
<<<<<<< HEAD
/**
 * Created by 94360 on 2018/2/21.
 */

>>>>>>> yuanzhi
public class Quiz {
    private int ID;
    Course course;
    List<Question> questions;
    //List<Answer> answers;

    public Quiz(String name, Course course, List<String> questions, List<String> answers, List<Double> points, int ID) {
=======
public class Quiz {
    final int ID;
    Course course;
    List<Question> questions;

    Quiz(Course course, List<String> questions, List<String> answers, List<Double> points, int ID) {
>>>>>>> 3663fb360364227b4436b150fdec5a468fa8f43e
        this.course = course;
        parseQuestions(questions, answers, points);
        this.ID = ID;
    }

<<<<<<< HEAD
=======
    Quiz(int ID, Course course) {
        this.ID = ID;
        this.course = course;
        questions = new ArrayList<>();
    }

>>>>>>> 3663fb360364227b4436b150fdec5a468fa8f43e
    private void parseQuestions(List<String> questions, List<String> answers, List<Double> points) {
        this.questions = new ArrayList<>();
        int amount = questions.size();
        for(int i=0;i<amount;i++) {
            this.questions.add(new Question(questions.get(i), answers.get(i), points.get(i), i));
        }
    }

<<<<<<< HEAD
    public void calculateGrade(Student student) {
=======
    void updateGrade(Student student) {
>>>>>>> 3663fb360364227b4436b150fdec5a468fa8f43e
        List<String> answer = student.answers.get(student.answers.size()-1);
        double grade = 0;
        for(int i=0;i<questions.size();i++) {
            if(answer.get(i).equals(questions.get(i).getAnswer())) grade += questions.get(i).points;
        }
        student.grades.add(grade);
    }

<<<<<<< HEAD
    public int getID() {
=======
    int getID() {
>>>>>>> 3663fb360364227b4436b150fdec5a468fa8f43e
        return ID;
    }
}

class Question {
    private int ID;
    String content;
    private String answer;
    double points;

    public Question(String content, String answer, double points, int ID) {
        //this.name = name;
        this.content = content;
        this.points = points;
        this.ID = ID;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public int getID() {
        return ID;
    }
}
