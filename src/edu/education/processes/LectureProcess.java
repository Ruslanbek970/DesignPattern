package edu.education.processes;

import edu.education.util.Logger;
import edu.education.model.Professor;

public class LectureProcess extends TeachingProcess {
    public LectureProcess(Professor p) { super(p, new edu.education.model.Lecture()); }
    @Override
    protected void prepare() {
        Logger.getInstance().log("[PREPARE] Checking slides and materials for lecture...");
    }
    @Override
    protected void finalizeProcess() {
        Logger.getInstance().log("[COMPLETE] Collecting questions and providing reading list.");
    }
}
