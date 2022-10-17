package com.example.ArticlesTracker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * todo Document type Status
 */
public enum Status {
    DONE("DONE"), IN_PROGRESS("IN_PROGRESS"), TO_BE_DONE("TO_BE_DONE");
    final String name;
    Status(String name) {
        this.name = name;
    }
    private static final Map<String, Status> lookup = new HashMap<String, Status>();

    static {
        for (Status i : Status.values()) {
            lookup.put(i.getName(), i);
        }
    }
    public String getName() {
        return name;
    }

    public static Status get(String name){
        return lookup.get(name);
    }
}
