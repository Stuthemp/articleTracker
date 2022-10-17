package com.example.ArticlesTracker.service.user;

import com.example.ArticlesTracker.repository.RoleRepository;
import com.example.ArticlesTracker.repository.UserRepository;
import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import com.example.ArticlesTracker.security.domain.Role;
import com.example.ArticlesTracker.security.domain.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * todo Document type UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void addUser(ArticleAppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void authenticate(ArticleAppUser user) {
        ArticleAppUser existingUser = findByLogin(user.getUsername());
    }

    @Override
    public ArticleAppUser findByLogin(String login) {
        return userRepository.findByUsername(login).orElseThrow();
    }

    @Override
    public void createUser(String username, String password, String email) {
        ArticleAppUser articleAppUser = new ArticleAppUser();
        articleAppUser.setEmail(email);
        articleAppUser.setUsername(username);
        articleAppUser.setPassword(passwordEncoder.encode(password));
        RoleEnum roleEnum = RoleEnum.get("ROLE_USER");
        Role role = roleRepository.findByName(roleEnum);
        articleAppUser.setRoles(Set.of(role));
        userRepository.save(articleAppUser);
    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.findByUsername(login).isPresent();
    }
}
