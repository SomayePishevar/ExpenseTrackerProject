package com.snapp.expensetracker.validator;

import com.snapp.expensetracker.repository.JpaUserRepository;
import com.snapp.expensetracker.payload.SignUpDto;
import com.snapp.expensetracker.validator.exception.DuplicatedInputException;
import com.snapp.expensetracker.validator.exception.InvalidInputDataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataValidator {

    @Autowired
    private JpaUserRepository userRepository;

    public void validateSignUpData(SignUpDto signUpDto) throws InvalidInputDataFormatException, DuplicatedInputException {

        if (signUpDto.getUsername().isEmpty() || signUpDto.getUsername() == null){
            throw new InvalidInputDataFormatException("username could not be empty or null.");
        }
        if (signUpDto.getUsername().length() < 6 || signUpDto.getUsername().length() > 40) {
            throw new InvalidInputDataFormatException("username must be between 6 and 40 characters.");
        }
        if (userRepository.findByUsername(signUpDto.getUsername())!=null){
            throw new DuplicatedInputException(signUpDto.getUsername() + " has been selected before");
        }

        if (signUpDto.getPassword().isEmpty() || signUpDto.getPassword() == null){
            throw new InvalidInputDataFormatException("password could not be empty or null.");
        }
        if (signUpDto.getPassword().length() < 6 || signUpDto.getUsername().length() > 10) {
            throw new InvalidInputDataFormatException("password must be between 6 and 10 characters.");
        }
        if (signUpDto.getPassword().equals(signUpDto.getConfirmPassword())){
            throw new InvalidInputDataFormatException("password and password confirmation are not the same ");
        }

        if (signUpDto.getPhoneNumber().isEmpty() || signUpDto.getPhoneNumber() == null){
            throw new InvalidInputDataFormatException("phone number could not be empty or null.");
        }
        if (!(signUpDto.getUsername().length() == 13 && signUpDto.getPhoneNumber().startsWith("+989"))) {
            throw new InvalidInputDataFormatException("phone number must be 13 characters and start with +989.");
        }
        if (userRepository.findByPhoneNumber(signUpDto.getPhoneNumber())!=null){
            throw new DuplicatedInputException(signUpDto.getPhoneNumber() + " has been selected before");
        }
    }
}
