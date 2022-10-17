package com.example.ArticlesTracker.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * todo Document type UserCreationForm
 */
public class UserCreationForm {
    private String email;
    private String username;
    private String password;

    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
}
