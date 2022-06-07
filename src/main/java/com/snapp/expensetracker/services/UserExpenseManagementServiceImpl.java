package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.entity.UserExpense;
import com.snapp.expensetracker.repository.JpaExpenseCategoryRepository;
import com.snapp.expensetracker.repository.JpaUserExpenseRepository;
import com.snapp.expensetracker.repository.JpaUserRepository;
import com.snapp.expensetracker.validator.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserExpenseManagementServiceImpl implements UserExpenseManagementService {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private JpaExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private JpaUserExpenseRepository userExpenseRepository;

    @Override
    public String addExpense(UserExpense userExpense, String categoryName, String username) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new ResourceNotFoundException("User", "username", username);
        }

        ExpenseCategory expenseCategory = expenseCategoryRepository.findByName(categoryName);
        if (expenseCategory == null){
            throw new ResourceNotFoundException("ExpenseCategory", "name", categoryName);
        }

        userExpense.setExpenseCategory(expenseCategory);
        userExpense.setUser(user);

        userExpenseRepository.save(userExpense);
        return checkAlert(userExpense);
    }

    private String checkAlert(UserExpense userExpense){
        List<ExpenseCategory> expenseCategories = new ArrayList<>();
        expenseCategories.add(userExpense.getExpenseCategory());
        BigInteger expenseCount = userExpenseRepository.periodicSumOfExpenses(userExpense.getExpenseDate(), userExpense.getStartCalculateExpenseDate(), expenseCategories, userExpense.getUser());
        int compareResult = userExpense.getExpenseCategory().getMaxExpense().compareTo(expenseCount);
        if (compareResult == -1 ){
            return "you have reached your maximum expense limitation";
        }else {
            return "you can spend money happily";
        }
    }
}
