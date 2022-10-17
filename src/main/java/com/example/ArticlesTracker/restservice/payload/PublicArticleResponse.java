package com.example.ArticlesTracker.restservice.payload;

import com.example.ArticlesTracker.model.Article;

/**
 * todo Document type PublicArticleResponse
 */
public class PublicArticleResponse {

    private String description;
    private String url;
    private String theme;

    public PublicArticleResponse(Article article) {
        this.description = article.getDescription();
        this.url = article.getUrl();
        this.theme = article.getTheme();
    }

    public PublicArticleResponse() {
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

}
