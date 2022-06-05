package com.snapp.expensetracker.services;

import com.snapp.expensetracker.validator.exception.UserNotFoundException;
import com.snapp.expensetracker.web.dto.UserExpenseDto;

public interface UserExpenseManagement {
    String addExpense(UserExpenseDto userExpenseDto) throws UserNotFoundException;
}
