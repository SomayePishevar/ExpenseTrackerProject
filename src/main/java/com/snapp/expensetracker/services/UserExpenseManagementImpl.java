package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.entity.UserExpense;
import com.snapp.expensetracker.repository.JpaExpenseCategoryRepository;
import com.snapp.expensetracker.repository.JpaUserExpenseRepository;
import com.snapp.expensetracker.repository.JpaUserRepository;
import com.snapp.expensetracker.validator.exception.CategoryNotFoundException;
import com.snapp.expensetracker.validator.exception.UserNotFoundException;
import com.snapp.expensetracker.payload.UserExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserExpenseManagementImpl implements UserExpenseManagement{

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private JpaExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private JpaUserExpenseRepository userExpenseRepository;

    @Override
    public String addExpense(UserExpenseDto userExpenseDto) throws UserNotFoundException {
//        try {
//            User user = userRepository.findByUsername(userExpenseDto.getUsername());
//            if (user == null){
//                throw new UserNotFoundException(userExpenseDto.getUsername());
//            }
//            ExpenseCategory expenseCategory = new ExpenseCategory();
//            List<ExpenseCategory> expenseCategoryList = expenseCategoryRepository.getAllExpenseCategoryName();
//            String expenseCategoryName = null;
//            for (String name : expenseCategoryList.){
//                if (userExpenseDto.getExpenseCategory().equals(name)){
//                    expenseCategoryName = name;
//                }
//            }
//            if (expenseCategoryName == null){
//                throw new CategoryNotFoundException(userExpenseDto.getExpenseCategory());
//            }else {
//                expenseCategory = expenseCategoryRepository.findByName(expenseCategoryName);
//            }
//
//            UserExpense userExpense = UserExpense.builder()
//                    .user(user)
//                    .expenseCategory(expenseCategory)
//                    .expenseAmount(userExpenseDto.getExpenseAmount())
//                    .expenseDate(userExpenseDto.getExpenseDate())
//                    .startCalculateExpenseDate(userExpenseDto.getStartCalculateExpenseDate())
//                    .build();
//
//            userExpenseRepository.save(userExpense);
//            return checkAlert(userExpense);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        return null;
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
