package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.payload.SignUpDto;

public interface UserService {
    void saveUser(User user);
    void saveAdmin(User user);
    User findByUsername(String username);
}
