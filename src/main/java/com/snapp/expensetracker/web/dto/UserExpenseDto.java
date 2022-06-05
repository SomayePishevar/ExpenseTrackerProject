package com.snapp.expensetracker.web.dto;


import com.snapp.expensetracker.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class UserExpenseDto {

    private String username;

    private String expenseCategory;

    private Date expenseDate;

    private BigInteger expenseAmount;

    private Date startCalculateExpenseDate;
}
