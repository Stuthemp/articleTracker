package com.example.ArticlesTracker.security.domain;

import com.example.ArticlesTracker.model.Importance;

import java.util.HashMap;
import java.util.Map;

/**
 * todo Document type RoleEnum
 */
public enum RoleEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_MODERATOR("ROLE_MODERATOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }

    private static final Map<String, RoleEnum> lookup = new HashMap<String, RoleEnum>();

    static {
        for (RoleEnum i : RoleEnum.values()) {
            lookup.put(i.getName(), i);
        }
    }
    public String getName() {
        return name;
    }

    public static RoleEnum get(String name){
        return lookup.get(name);
    }


}
