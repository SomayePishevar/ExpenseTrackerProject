package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.UserExpense;
import com.snapp.expensetracker.exception.ResourceNotFoundException;

public interface UserExpenseManagementService {
    String addExpense(UserExpense userExpense, String categoryName, String username) throws ResourceNotFoundException;
}
