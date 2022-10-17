package com.example.ArticlesTracker.service.role;

import com.example.ArticlesTracker.security.domain.Role;
import com.example.ArticlesTracker.security.domain.RoleEnum;

/**
 * todo Document type RoleService
 */
public interface RoleService {

    Role findByName(RoleEnum name);

}
