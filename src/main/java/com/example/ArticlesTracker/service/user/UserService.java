package com.example.ArticlesTracker.service.user;

import com.example.ArticlesTracker.security.domain.ArticleAppUser;

/**
 * todo Document type UserService
 */
public interface UserService {

    void addUser(ArticleAppUser user);
    void authenticate(ArticleAppUser user);
    ArticleAppUser findByLogin(String login);
    void createUser(String username, String password, String email);
    boolean existsByLogin(String login);
}
