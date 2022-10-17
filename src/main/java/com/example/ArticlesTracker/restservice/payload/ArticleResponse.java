package com.example.ArticlesTracker.restservice.payload;

import com.example.ArticlesTracker.model.Article;
import com.example.ArticlesTracker.model.Importance;
import com.example.ArticlesTracker.model.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * todo Document type ArticleResponse
 */
public class ArticleResponse {

    private String description;
    private String url;
    private String theme;
    private String status;
    private String importance;

    public ArticleResponse(Article article) {
        this.description = article.getDescription();
        this.url = article.getUrl();
        this.theme = article.getTheme();
        this.status = article.getStatus().getName();
        this.importance = article.getImportance().getName();
    }

    public ArticleResponse() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
