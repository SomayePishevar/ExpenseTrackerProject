package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaUserRepository extends JpaRepository<User , Long> {

    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);

    @Query("Select u from User u Where u.username =: username and u.password =: password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
