package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User , Long> {

    User findByUsername(String username);
}
