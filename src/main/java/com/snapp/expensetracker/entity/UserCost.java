package com.snapp.expensetracker.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "usercosts")
public class UserCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn
    private User user;

    @OneToOne
    @JoinColumn
    private ExpenseCategory expenseCategory;

    @Column(nullable = false)
    private String costDate;

    @Column(nullable = false)
    private BigInteger costAmount;
}
