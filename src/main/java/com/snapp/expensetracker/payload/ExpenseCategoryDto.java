package com.snapp.expensetracker.payload;

import com.sun.istack.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ExpenseCategoryDto {

    private Long Id;

    @NotNull
    private String name;

    @NotNull
    private BigInteger maxAmount;
}
