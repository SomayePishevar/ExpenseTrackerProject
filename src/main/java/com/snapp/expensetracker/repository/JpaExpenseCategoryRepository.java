package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long>{
    @Query("Select c.name from ExpenseCategory c")
    List<String> getAllExpenseCategoryName();

    ExpenseCategory findByName(String name);
}
