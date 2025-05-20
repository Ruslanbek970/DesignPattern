package edu.education.decorator;

import edu.education.model.Teaching;
import edu.education.util.Logger;
public class SessionDecorator implements Teaching {
    private final Teaching wrapped;
    private final String sessionInfo;
    public SessionDecorator(Teaching teaching, String info) {
        this.wrapped = teaching;
        this.sessionInfo = info;
    }
    @Override
    public void execute() {
        Logger.getInstance().log("[SESSION INFO] " + sessionInfo);
        wrapped.execute();
    }
    @Override
    public String getDescription() {
        return wrapped.getDescription() + " (" + sessionInfo + ")";
    }
}
