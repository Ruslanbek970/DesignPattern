package edu.education.model;

import edu.education.util.Logger;

public class HomeworkAssignment implements Teaching {
    @Override
    public void execute() {
        Logger.getInstance().log("Professor assigns a homework on design patterns.");
    }
    @Override
    public String getDescription() {
        return "Homework assignment on design patterns";
    }
}
