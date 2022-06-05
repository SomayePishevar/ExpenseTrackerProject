package com.snapp.expensetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@Table(name = "userExpenses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserExpense {

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
    private Date expenseDate;

    @Column(nullable = false)
    private BigInteger expenseAmount;

    @Transient
    private Date startCalculateExpenseDate;
}
