package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.web.dto.UserDto;

public interface UserService {
    void saveUser(UserDto user);
    void saveAdmin(UserDto user);
    User findByUsername(String username);
}
