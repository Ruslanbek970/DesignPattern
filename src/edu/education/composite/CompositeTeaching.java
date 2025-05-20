package edu.education.composite;

import edu.education.model.Teaching;
import java.util.ArrayList;
import java.util.List;

public class CompositeTeaching implements Teaching {
    private final List<Teaching> children = new ArrayList<>();
    public void add(Teaching t) { children.add(t); }
    public void remove(Teaching t) { children.remove(t); }
    @Override
    public void execute() {
        for (Teaching t : children) {
            t.execute();
        }
    }
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < children.size(); i++) {
            if (i > 0) sb.append(" + ");
            sb.append(children.get(i).getDescription());
        }
        return sb.toString();
    }
}
