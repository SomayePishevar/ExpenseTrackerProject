package com.snapp.expensetracker.services;

import com.snapp.expensetracker.validator.exception.UserNotFoundException;
import com.snapp.expensetracker.payload.UserExpenseDto;

public interface UserExpenseManagement {
    String addExpense(UserExpenseDto userExpenseDto) throws UserNotFoundException;
}
