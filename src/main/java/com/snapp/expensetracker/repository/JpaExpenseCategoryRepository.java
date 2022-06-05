package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long>{
}
