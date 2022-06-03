package com.snapp.expensetracker.web.dto;

import com.snapp.expensetracker.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;

    private String password;

    private String confirmPassword;

    private String phoneNumber;
}
