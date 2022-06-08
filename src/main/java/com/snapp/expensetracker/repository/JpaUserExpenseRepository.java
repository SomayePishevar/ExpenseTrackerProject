package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.sql.Timestamp;

public interface JpaUserExpenseRepository extends JpaRepository<UserExpense, Long> {

    @Query(value = "select sum(e.expenseAmount) from UserExpense e where e.expenseCategory.Id = :expenseCategoryId and e.user.id = :userId and e.expenseDate between :startDate and current_timestamp", nativeQuery = false)
    BigInteger periodicSumOfExpenses(@Param("startDate") Timestamp startDate, @Param("expenseCategoryId") Long expenseCategoryId, @Param("userId") Long userId);

}
