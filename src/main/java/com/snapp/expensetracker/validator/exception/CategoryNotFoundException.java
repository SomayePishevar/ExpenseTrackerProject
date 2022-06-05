package com.snapp.expensetracker.validator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryNotFoundException extends Exception{
    private String expenseCategoryName;
}
