package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.repository.JpaExpenseCategoryRepository;
import com.snapp.expensetracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService{

    @Autowired
    private JpaExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {
        List<ExpenseCategory> expenseCategoryList = expenseCategoryRepository.getAllExpenseCategories();
        return expenseCategoryList ;
    }

    @Override
    public List<String> getAllExpenseCategoryName() {
        List<String> expenseCategoryNameList = expenseCategoryRepository.getAllExpenseCategoryNames();
        return expenseCategoryNameList ;
    }

    @Override
    public ExpenseCategory save(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    @Override
    public ExpenseCategory findById(long id) {

        ExpenseCategory expenseCategory = expenseCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ExpenseCategory", "Id", id));
        return expenseCategory;
    }

    @Override
    public ExpenseCategory update(ExpenseCategory expenseCategory, long id) {
        ExpenseCategory existedExpenseCategory = expenseCategoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("ExpenseCategory", "Id", id));
        existedExpenseCategory.setMaxExpense(expenseCategory.getMaxExpense());
        existedExpenseCategory.setName(expenseCategory.getName());
        ExpenseCategory updatedExpenseCategory = expenseCategoryRepository.save(existedExpenseCategory);
        return updatedExpenseCategory;
    }

    @Override
    public void deleteById(long id) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("ExpenseCategory", "Id", id));
        expenseCategoryRepository.delete(expenseCategory);
    }
}
