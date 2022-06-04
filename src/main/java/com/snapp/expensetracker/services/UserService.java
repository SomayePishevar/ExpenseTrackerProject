package com.snapp.expensetracker.services;

import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.validator.exception.UserNotFoundException;
import com.snapp.expensetracker.web.dto.UserDto;

public interface UserService {
    void saveUser(UserDto user);
    void saveAdmin(UserDto user);
    void login(String username, String password) throws UserNotFoundException;
    User findByUsername(String username);
}
