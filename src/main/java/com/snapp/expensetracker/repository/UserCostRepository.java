package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.UserCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCostRepository extends JpaRepository<UserCost, Long> {
}
