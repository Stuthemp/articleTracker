package com.example.ArticlesTracker.service.role;

import com.example.ArticlesTracker.repository.RoleRepository;
import com.example.ArticlesTracker.security.domain.Role;
import com.example.ArticlesTracker.security.domain.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * todo Document type RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(RoleEnum name) {
        return roleRepository.findByName(name);
    }
}
