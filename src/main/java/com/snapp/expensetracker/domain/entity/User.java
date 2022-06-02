package com.snapp.expensetracker.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 40)
    private String userName;

    @Column(nullable = false , length = 64)
    private String password;

    @Column(nullable = false, unique = true, length = 45)
    private String email;
}
