package com.snapp.expensetracker.payload;


import com.snapp.expensetracker.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Data
public class UserExpenseDto {

    @NotNull
    private String expenseCategoryName;

    @NotNull
    private Date expenseDate;

    @NotNull
    private BigInteger expenseAmount;

    @NotNull
    private Date startCalculateExpenseDate;
}
