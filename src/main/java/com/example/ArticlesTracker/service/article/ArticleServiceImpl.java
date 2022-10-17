package com.example.ArticlesTracker.service.article;

import com.example.ArticlesTracker.model.Article;
import com.example.ArticlesTracker.model.Importance;
import com.example.ArticlesTracker.model.Status;
import com.example.ArticlesTracker.repository.ArticleRepository;
import com.example.ArticlesTracker.repository.UserRepository;
import com.example.ArticlesTracker.restservice.payload.ArticleResponse;
import com.example.ArticlesTracker.restservice.payload.PublicArticleResponse;
import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import com.example.ArticlesTracker.service.user.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * todo Document type ArticleServiceImpl
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;

    private UserService userService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    @Override
    public Article findByDescription(String description) {
        return null;
    }
    @Override
    public List<Article> findByUser(ArticleAppUser user) {
        return articleRepository.findByUser(user);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void createArticle(String description, String url, String theme, String importance, String status, String login) {
        Article article = new Article();
        article.setDescription(description);
        article.setUrl(url);
        article.setTheme(theme);
        article.setImportance(Importance.get(importance));
        article.setStatus(Status.get(status));
        article.setUser(userService.findByLogin(login));
        articleRepository.save(article);
    }

    @Override
    public Map<String, Object> findPage(Pageable paging) {
        List<PublicArticleResponse> articles;
        Page<Article> articlePage = articleRepository.findAll(paging);
        articles = articlePage.getContent().stream().map(PublicArticleResponse::new).collect(Collectors.toList());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("articles",articles);
        responseData.put("currentPage",articlePage.getNumber());
        responseData.put("totalItems", articlePage.getTotalElements());
        responseData.put("totalPages", articlePage.getTotalPages());

        return responseData;
    }
    @Override
    public Map<String, Object> findByFilters(ArticleAppUser user, String statusName, String importanceName, String theme, Pageable pageable) throws Exception {
        List<ArticleResponse> articles;
        Page<Article> articlePage;

        if(statusName.equals("ANY") && importanceName.equals("ANY") && theme.isEmpty()) {
            throw new Exception("Bad params");
        } else if (statusName.equals("ANY") && importanceName.equals("ANY")) {
            articlePage = articleRepository.findByUserAndTheme(user, theme, pageable);
        } else if (statusName.equals("ANY") && theme.isEmpty()) {
            Importance importance = Importance.get(importanceName);
            articlePage = articleRepository.findByUserAndImportance(user, importance, pageable);
        } else if (importanceName.equals("ANY") && theme.isEmpty()) {
            Status status = Status.get(statusName);
            articlePage = articleRepository.findByUserAndStatus(user, status, pageable);
        } else if (importanceName.equals("ANY")) {
            Status status = Status.get(statusName);
            articlePage = articleRepository.findByUserAndStatusAndTheme(user, status, theme, pageable);
        } else if (statusName.equals("ANY")) {
            Importance importance = Importance.get(importanceName);
            articlePage = articleRepository.findByUserAndImportanceAndTheme(user, importance, theme, pageable);
        } else if (theme.isEmpty()) {
            Importance importance = Importance.get(importanceName);
            Status status = Status.get(statusName);
            articlePage = articleRepository.findByUserAndStatusAndImportance(user, status, importance, pageable);
        } else {
            Importance importance = Importance.get(importanceName);
            Status status = Status.get(statusName);
            articlePage = articleRepository.findByUserAndStatusAndImportanceAndTheme(user, status, importance, theme, pageable);
        }

        articles = articlePage.getContent().stream().map(ArticleResponse::new).collect(Collectors.toList());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("articles", articles);
        responseData.put("currentPage",articlePage.getNumber());
        responseData.put("totalItems",articlePage.getTotalElements());
        responseData.put("totalPages",articlePage.getTotalPages());

        return responseData;
    }
}
