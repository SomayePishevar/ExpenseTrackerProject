package com.snapp.expensetracker.entity;

import com.snapp.expensetracker.common.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true, length = 40)
    private String username;

    @Column(nullable = false )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @Column(nullable = false, unique = true, length = 13)
    private String phoneNumber;
}
