package com.snapp.expensetracker.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true, length = 40)
    private String username;

    @Column(nullable = false , length = 64)
    private String password;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @ManyToMany
    private Set<Role> roles;
}
