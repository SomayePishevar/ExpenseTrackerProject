package com.snapp.expensetracker.validator.exception;

import lombok.Data;

@Data
public class DuplicatedInputException extends RuntimeException {

    public DuplicatedInputException(String message){
        super(message);
    }
}
