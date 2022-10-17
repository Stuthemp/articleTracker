package com.example.ArticlesTracker.repository;

import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * todo Document type UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<ArticleAppUser, Long> {

    Optional<ArticleAppUser> findByUsername(String username);

}
