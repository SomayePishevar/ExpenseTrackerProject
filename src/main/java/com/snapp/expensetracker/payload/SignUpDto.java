package com.snapp.expensetracker.payload;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SignUpDto {

    private String username;

    private String password;

    private String confirmPassword;

    private String phoneNumber;
}
