package com.example.ArticlesTracker.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * todo Document type ArticleCreationForm
 */
public class ArticleCreationForm {

    private String description;
    private String url;
    private String status;
    private String importance;
    private String theme;
    private String login;

    public String getLogin() {
        return login;
    }

    @JsonProperty("username")
    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportance() {
        return importance;
    }

    @JsonProperty("importance")
    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getTheme() {
        return theme;
    }

    @JsonProperty("theme")
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
