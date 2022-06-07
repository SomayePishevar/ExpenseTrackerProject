package com.snapp.expensetracker.controller;

import com.snapp.expensetracker.entity.UserExpense;
import com.snapp.expensetracker.payload.UserExpenseDto;
import com.snapp.expensetracker.services.UserExpenseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/userOperation")
public class UserExpenseManagementController {


    @Autowired
    private UserExpenseManagementService userExpenseManagementService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/expenseManagement")
    public ResponseEntity<String> addExpense(@Valid @RequestBody UserExpenseDto userExpenseDto, Principal principal){
        UserExpense userExpense = UserExpense.builder()
                .expenseAmount(userExpenseDto.getExpenseAmount())
                .expenseDate(userExpenseDto.getExpenseDate())
                .startCalculateExpenseDate(userExpenseDto.getStartCalculateExpenseDate())
                .build();
        String checkAlertMessage = userExpenseManagementService.addExpense(userExpense, userExpenseDto.getExpenseCategoryName(), principal.getName());
        return new ResponseEntity<>(checkAlertMessage, HttpStatus.OK);

    }
}
