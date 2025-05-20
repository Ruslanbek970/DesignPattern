package edu.education.adapter;

import edu.education.observer.Observer;
import edu.education.util.Logger;
import edu.education.model.Student;

public class AnalyticsAdapter implements Observer {
    private final Student student;

    public AnalyticsAdapter(Student student) {
        this.student = student;
    }

    @Override
    public void update(String message) {
        if (message.contains("Lecture")) {
            student.incrementLectures();
        }
        else if (message.contains("Homework assignment")) {
            student.incrementHomeworks();
        }
        else if (message.contains("Homework review")) {
            student.incrementReviews();
            int score = (int)(Math.random() * 40) + 60;
            student.addScore(score);
        }

        Logger.getInstance().log(
                student.getName() + " updated:\n" +
                        "Lectures: " + student.getLectures() + "\n" +
                        "Homeworks: " + student.getHomeworks() + "\n" +
                        "Reviews: " + student.getReviews() + "\n" +
                        "Average Score: " + student.getAverageScore() + "\n"
        );
    }
}