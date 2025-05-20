package edu.education.util;

import java.util.*;
import java.util.function.Consumer;

public class Logger {
    private static Logger instance;
    private final List<Consumer<String>> listeners = new ArrayList<>();

    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }

    public void log(String msg) {
        System.out.println(msg);
        listeners.forEach(l -> l.accept(msg));
    }

    public void addListener(Consumer<String> l) {
        listeners.add(l);
    }
}
