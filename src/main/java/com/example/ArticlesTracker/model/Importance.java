package com.example.ArticlesTracker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * todo Document type Importance
 */
public enum Importance {
    HIGH("HIGH"), MEDIUM("MEDIUM"), LOW("LOW");
    final String name;
    Importance(String name) {
        this.name = name;
    }
    private static final Map<String, Importance> lookup = new HashMap<String, Importance>();

    static {
        for (Importance i : Importance.values()) {
            lookup.put(i.getName(), i);
        }
    }
    public String getName() {
        return name;
    }

    public static Importance get(String name){
        return lookup.get(name);
    }
}
