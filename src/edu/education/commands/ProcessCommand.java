package edu.education.commands;

import edu.education.processes.TeachingProcess;

public class ProcessCommand<T extends TeachingProcess> implements Command {
    private final T process;
    public ProcessCommand(T process) {
        this.process = process;
    }
    @Override
    public void execute() {
        process.run();
    }
}
