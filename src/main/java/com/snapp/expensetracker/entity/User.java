package com.snapp.expensetracker.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true, length = 40)
    private String username;

    @Column(nullable = false )
    private String password;

    @Transient
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false, length = 13)
    private String phoneNumber;
}