package com.example.ArticlesTracker.model;

import com.example.ArticlesTracker.security.domain.ArticleAppUser;

import javax.persistence.*;

/**
 * todo Document type Article
 */
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_generator")
    @SequenceGenerator(name = "article_id_generator", sequenceName = "articles_id_seq", allocationSize = 1)
    private Long id;
    private String description;
    private String url;

    private String theme;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Importance importance;

    @ManyToOne
    @JoinTable(name = "article_user",
               joinColumns = @JoinColumn(name = "article_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
    private ArticleAppUser user;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Status getStatus() {
        return status;
    }

    public Importance getImportance() {
        return importance;
    }

    public ArticleAppUser getUser() {
        return user;
    }

    public void setUser(ArticleAppUser user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }
}
