package edu.education.commands;

import edu.education.model.Teaching;
import edu.education.model.Professor;


public class TeachingCommand implements Command {
    private final Professor prof;
    private final Teaching teaching;
    public TeachingCommand(Professor p, Teaching t) {
        prof = p;
        teaching = t;
    }
    @Override
    public void execute() {
        prof.setTeachingStrategy(teaching);
        prof.performTeaching();
    }
}
