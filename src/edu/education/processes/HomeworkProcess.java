package edu.education.processes;

import edu.education.util.Logger;
import edu.education.model.Professor;

public class HomeworkProcess extends TeachingProcess {
    public HomeworkProcess(Professor p) {
        super(p, new edu.education.model.HomeworkAssignment());
    }

    @Override
    protected void prepare() {
        Logger.getInstance().log("Preparing homework materials...");
    }

    @Override
    protected void finalizeProcess() {
        Logger.getInstance().log("Homework submission closed");
    }
}