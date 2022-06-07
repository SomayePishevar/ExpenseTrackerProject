package com.snapp.expensetracker.payload;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryDto {

    private Long Id;

    @NotNull
    private String name;

    @NotNull
    private BigInteger maxExpense;
}
