package edu.education.model;

import edu.education.util.Logger;

public class HomeworkReview implements Teaching {
    @Override
    public void execute() {
        Logger.getInstance().log("Professor reviews the students' homework.");
    }
    @Override
    public String getDescription() {
        return "Homework review";
    }
}
