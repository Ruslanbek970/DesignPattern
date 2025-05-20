package edu.education.model;

import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty lectures = new SimpleIntegerProperty(0);
    private final IntegerProperty homeworks = new SimpleIntegerProperty(0);
    private final IntegerProperty reviews = new SimpleIntegerProperty(0);
    private final List<Integer> scores = new ArrayList<>();
    private final DoubleProperty averageScore = new SimpleDoubleProperty(0.0);

    public Student(String name) {
        this.name.set(name);
    }

    public String getName() { return name.get(); }
    public int getLectures() { return lectures.get(); }
    public int getHomeworks() { return homeworks.get(); }
    public int getReviews() { return reviews.get(); }
    public double getAverageScore() { return averageScore.get(); }

    public StringProperty nameProperty() { return name; }
    public IntegerProperty lecturesProperty() { return lectures; }
    public IntegerProperty homeworksProperty() { return homeworks; }
    public IntegerProperty reviewsProperty() { return reviews; }
    public DoubleProperty averageScoreProperty() { return averageScore; }

    public void incrementLectures() { lectures.set(lectures.get() + 1); }
    public void incrementHomeworks() { homeworks.set(homeworks.get() + 1); }
    public void incrementReviews() { reviews.set(reviews.get() + 1); }

    public void addScore(int score) {
        scores.add(score);
        updateAverage();
    }

    private void updateAverage() {
        double avg = scores.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        averageScore.set(Math.round(avg * 100.0) / 100.0);
    }
}