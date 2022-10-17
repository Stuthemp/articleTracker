package com.example.ArticlesTracker.service.article;

import com.example.ArticlesTracker.model.Article;
import com.example.ArticlesTracker.model.Importance;
import com.example.ArticlesTracker.model.Status;
import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;


import java.util.List;
import java.util.Map;

/**
 * todo Document type ArticleService
 */
public interface ArticleService {

    Article findByDescription(String description);
    List<Article> findByUser(ArticleAppUser user);
    List<Article> findAll();
    void createArticle(String description, String url, String theme, String importance, String status, String login);
    Map<String, Object> findPage(Pageable paging);
    Map<String, Object> findByFilters(ArticleAppUser user, String status, String importance, String theme, Pageable pageable) throws Exception;
}
