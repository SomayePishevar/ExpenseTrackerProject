package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.entity.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface JpaUserExpenseRepository extends JpaRepository<UserExpense, Long> {

    @Query(value = "select sum(e.expenseAmount) from UserExpense e where e.expenseDate >= trunc(SYSDATE) - startdate and e.expenseCategory in expenseCategories and e.user = user", nativeQuery = true)
    BigInteger periodicSumOfExpenses(@Param("expenseDate") Date expenseDate, @Param("startDate") Date startDate, @Param("expenseCategories") List<ExpenseCategory> expenseCategories, @Param("user") User user);

    @Query(value = "select e.expenseAmount from UserExpense e having e.expenseDate >= trunc(SYSDATE) - startdate and e.user = user group by expenseCategories ", nativeQuery = true)
    List<BigInteger> costByExpenseCategory(@Param("expenseDate") Date expenseDate, @Param("startDate") Date startDate, @Param("expenseCategories") List<ExpenseCategory> expenseCategories, @Param("user") User user);
}
