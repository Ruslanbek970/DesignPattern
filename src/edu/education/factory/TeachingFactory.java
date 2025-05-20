package edu.education.factory;

import edu.education.model.Lecture;
import edu.education.model.HomeworkAssignment;
import edu.education.model.HomeworkReview;
import edu.education.model.Teaching;


public class TeachingFactory {
    public static Teaching createTeaching(String type) {
        if (type.equalsIgnoreCase("Lecture")) {
            return new Lecture();
        } else if (type.equalsIgnoreCase("HomeworkAssignment")) {
            return new HomeworkAssignment();
        } else if (type.equalsIgnoreCase("HomeworkReview")) {
            return new HomeworkReview();
        } else {
            throw new IllegalArgumentException("Unknown teaching type: " + type);
        }
    }
}
