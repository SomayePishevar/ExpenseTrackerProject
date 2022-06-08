package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.common.ExpenseSumPerCategory;
import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.entity.UserExpense;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface JpaUserExpenseRepository extends JpaRepository<UserExpense, Long> {

    @Query(value = "select sum(e.expenseAmount) from UserExpense e where e.expenseCategory.Id = :expenseCategoryId and e.user.id = :userId and e.expenseDate between :startDate and current_timestamp", nativeQuery = false)
    BigInteger periodicSumOfExpenses(@Param("startDate") Timestamp startDate, @Param("expenseCategoryId") Long expenseCategoryId, @Param("userId") Long userId);

}
