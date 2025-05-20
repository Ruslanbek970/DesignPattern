package edu.education.processes;

import edu.education.model.Teaching;
import edu.education.model.Professor;
import edu.education.util.Logger;
import java.time.LocalDateTime;

interface ProcessState {
    void onEnter(TeachingProcess proc);
}

class ScheduledState implements ProcessState {
    @Override
    public void onEnter(TeachingProcess proc) {
        Logger.getInstance().log("[STATE] Scheduled: " + proc.getDescription());
    }
}

class RunningState implements ProcessState {
    @Override
    public void onEnter(TeachingProcess proc) {
        Logger.getInstance().log("[STATE] Running: " + proc.getDescription());
    }
}

class CompletedState implements ProcessState {
    @Override
    public void onEnter(TeachingProcess proc) {
        Logger.getInstance().log("[STATE] Completed: " + proc.getDescription());
    }
}

public abstract class TeachingProcess {
    protected final Professor prof;
    protected final Teaching teaching;
    private ProcessState state;
    private LocalDateTime scheduledTime;

    public TeachingProcess(Professor p, Teaching t) {
        this.prof = p;
        this.teaching = t;
        this.scheduledTime = LocalDateTime.now();
    }

    public final void run() {
        changeState(new ScheduledState());
        prepare();
        changeState(new RunningState());
        prof.setTeachingStrategy(teaching);
        prof.performTeaching();
        finalizeProcess();
        changeState(new CompletedState());
    }

    protected void prepare() {}
    protected void finalizeProcess() {}

    private void changeState(ProcessState newState) {
        this.state = newState;
        state.onEnter(this);
    }

    public String getDescription() {
        return teaching.getDescription();
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime time) {
        this.scheduledTime = time;
    }
}