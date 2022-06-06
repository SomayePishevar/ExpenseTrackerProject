package com.snapp.expensetracker.payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {

    private String username;

    private String password;

    private String confirmPassword;

    private String phoneNumber;
}
