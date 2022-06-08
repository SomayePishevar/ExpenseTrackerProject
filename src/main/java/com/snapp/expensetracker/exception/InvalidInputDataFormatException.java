package com.snapp.expensetracker.exception;

import lombok.Data;

@Data
public class InvalidInputDataFormatException extends RuntimeException{

    public InvalidInputDataFormatException(String message){
        super(message);
    }
}
