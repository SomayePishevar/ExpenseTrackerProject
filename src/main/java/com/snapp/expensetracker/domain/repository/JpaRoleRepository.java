package com.snapp.expensetracker.domain.repository;

import com.snapp.expensetracker.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {
}
