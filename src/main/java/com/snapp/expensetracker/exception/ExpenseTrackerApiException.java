package com.snapp.expensetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExpenseTrackerApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;


}
