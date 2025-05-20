package edu.education.processes;

import edu.education.model.Professor;
import edu.education.util.Logger;

public class ReviewProcess extends TeachingProcess {
    public ReviewProcess(Professor p) {
        super(p, new edu.education.model.HomeworkReview());
    }

    @Override
    protected void prepare() {
        Logger.getInstance().log("Collecting submissions...");
    }

    @Override
    protected void finalizeProcess() {
        Logger.getInstance().log("Review completed");
    }
}