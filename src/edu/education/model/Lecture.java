package edu.education.model;

import edu.education.util.Logger;

public class Lecture implements Teaching {
    @Override
    public void execute() {
        Logger.getInstance().log("Professor conducts a lecture on design patterns.");
    }
    @Override
    public String getDescription() {
        return "Lecture on design patterns";
    }
}
