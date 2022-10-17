package com.example.ArticlesTracker.security.service;

import com.example.ArticlesTracker.repository.UserRepository;
import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * todo Document type JpaUserDetailsService
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException(
            "Problem during authentication"
        );

        ArticleAppUser user = userRepository
            .findByUsername(login)
            .orElseThrow(s);

        return new CustomUserDetails(user);
    }
}
