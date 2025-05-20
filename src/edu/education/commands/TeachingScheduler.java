package edu.education.commands;

public class TeachingScheduler implements Command {
    private final Command cmd;
    private final long delay;
    public TeachingScheduler(Command cmd, long delay) {
        this.cmd = cmd;
        this.delay = delay;
    }
    @Override
    public void execute() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        cmd.execute();
    }
}
