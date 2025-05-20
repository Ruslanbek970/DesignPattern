package edu.education.facade;

import edu.education.model.Professor;
import edu.education.model.Student;
import edu.education.commands.*;
import edu.education.adapter.AnalyticsAdapter;
import edu.education.processes.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.util.Iterator;

public class TeachingFacade implements Iterable<TeachingProcess> {
    private final Professor prof;
    private final ObservableList<TeachingProcess> processes = FXCollections.observableArrayList();
    private final java.util.List<Student> students = new java.util.ArrayList<>();

    public TeachingFacade(Professor p) {
        prof = p;
    }

    public void registerStudent(String name) {
        Student student = new Student(name);
        students.add(student);
        prof.addStudent(new AnalyticsAdapter(student));
    }

    public void processLecture(long delay) {
        addProcess(new LectureProcess(prof), delay);
    }

    public void processAssignment(long delay) {
        addProcess(new HomeworkProcess(prof), delay);
    }

    public void processReview(long delay) {
        addProcess(new ReviewProcess(prof), delay);
    }

    public void processFullSession(long delay) {
        addProcess(new LectureProcess(prof), delay);
        addProcess(new HomeworkProcess(prof), delay + 1000);
        addProcess(new ReviewProcess(prof), delay + 2000);
    }

    private void addProcess(TeachingProcess proc, long delay) {
        proc.setScheduledTime(LocalDateTime.now().plusSeconds(delay / 1000));
        Platform.runLater(() -> processes.add(proc));

        new Thread(() -> {
            try {
                Thread.sleep(delay);
                Platform.runLater(() -> new ProcessCommand<>(proc).execute());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public ObservableList<TeachingProcess> getProcesses() { return processes; }
    public java.util.List<Student> getStudents() { return students; }

    @Override
    public Iterator<TeachingProcess> iterator() {
        return processes.iterator();
    }
}