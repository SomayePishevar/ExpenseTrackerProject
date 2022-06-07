package com.snapp.expensetracker.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.snapp.expensetracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExpenseDto {

    @NotNull
    private String expenseCategoryName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private Date expenseDate;

    @NotNull
    private BigInteger expenseAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private Date startCalculateExpenseDate;
}
