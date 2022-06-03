package com.snapp.expensetracker.validator;

import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.service.UserService;
import com.snapp.expensetracker.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserDataValidator implements Validator {

    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 40){
            errors.rejectValue("username", "UsernameInvalidLength");
        }
        if (userService.findByUsername(user.getUsername())!=null){
            errors.rejectValue("username", "DuplicateUsername");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length()<6 || user.getPassword().length()>10){
            errors.rejectValue("password", "PasswordInvalidLength");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("passwordConfirm", "Password&ConfirmationNotEqual");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty");
        if (user.getPassword().length()!=13){
            errors.rejectValue("phoneNumber", "phoneNumberInvalidLength");
        }
    }
}
