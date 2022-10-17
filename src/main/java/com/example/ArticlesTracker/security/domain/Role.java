package com.example.ArticlesTracker.security.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * todo Document type Role
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_generator")
    @SequenceGenerator(name = "role_id_generator", sequenceName = "roles_id_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}
