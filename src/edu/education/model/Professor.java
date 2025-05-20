package edu.education.model;

import edu.education.observer.Observer;
import edu.education.util.Logger;
import java.util.ArrayList;
import java.util.List;


public class Professor {
    private Teaching strategy;
    private final List<Observer> students = new ArrayList<>();
    public void setTeachingStrategy(Teaching strategy) {
        this.strategy = strategy;
    }
    public void performTeaching() {
        if (strategy != null) {
            strategy.execute();
            Logger.getInstance().log("Session conducted: " + strategy.getDescription());
            notifyStudents("New session: " + strategy.getDescription());
        }
    }
    public void addStudent(Observer o) {
        students.add(o);
    }
    private void notifyStudents(String msg) {
        for (Observer o : students) {
            o.update(msg);
        }
    }
}
