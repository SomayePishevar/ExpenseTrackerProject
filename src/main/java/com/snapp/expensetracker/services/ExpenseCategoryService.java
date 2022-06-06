package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService {

    List<String> getAllExpenseCategoryName();
    ExpenseCategory save(ExpenseCategory expenseCategory);
}
