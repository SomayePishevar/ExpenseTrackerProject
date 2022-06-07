package com.snapp.expensetracker.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedInputException extends RuntimeException {

    public DuplicatedInputException(String message){
        super(message);
    }
}
