package com.example.ArticlesTracker.repository;

import com.example.ArticlesTracker.model.Article;
import com.example.ArticlesTracker.model.Importance;
import com.example.ArticlesTracker.model.Status;
import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * todo Document type ArticleRepository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByUser(ArticleAppUser user);
    Page<Article> findAll(Pageable pageable);
    Page<Article> findByUserAndStatus(ArticleAppUser user, Status status, Pageable pageable);
    Page<Article> findByUserAndImportance(ArticleAppUser user, Importance importance, Pageable pageable);
    Page<Article> findByUserAndTheme(ArticleAppUser user, String theme, Pageable pageable);

    Page<Article> findByUserAndStatusAndImportance(ArticleAppUser user, Status status ,Importance importance, Pageable pageable);
    Page<Article> findByUserAndStatusAndTheme(ArticleAppUser user, Status status, String theme, Pageable pageable);
    Page<Article> findByUserAndImportanceAndTheme(ArticleAppUser user, Importance importance, String theme, Pageable pageable);
    Page<Article> findByUserAndStatusAndImportanceAndTheme(ArticleAppUser user, Status status, Importance importance, String theme, Pageable pageable);

}
