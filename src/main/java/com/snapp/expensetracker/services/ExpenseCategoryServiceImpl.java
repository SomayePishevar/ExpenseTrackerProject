package com.snapp.expensetracker.services;

import com.snapp.expensetracker.repository.JpaExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService{

    @Autowired
    private JpaExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public List<String> getAllExpenseCategoryName() {
        List<String> expenseCategoryName = expenseCategoryRepository.getAllExpenseCategoryName();
        return expenseCategoryName ;
    }
}
