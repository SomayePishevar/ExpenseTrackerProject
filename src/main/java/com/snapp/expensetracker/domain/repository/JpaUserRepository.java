package com.snapp.expensetracker.domain.repository;

import com.snapp.expensetracker.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User , Long> {
}
