package com.example.ArticlesTracker.repository;

import com.example.ArticlesTracker.security.domain.Role;
import com.example.ArticlesTracker.security.domain.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * todo Document type RoleRepository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleEnum name);

}
