package com.snapp.expensetracker.validator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DuplicatedInputException extends Exception {

    private String message;

    public DuplicatedInputException(String message){
        super(message);
    }
}
