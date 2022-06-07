package com.snapp.expensetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExpenseTrackerApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;


}
