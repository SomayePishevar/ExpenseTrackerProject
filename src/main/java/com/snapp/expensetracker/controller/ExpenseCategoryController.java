package com.snapp.expensetracker.controller;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.payload.ExpenseCategoryDto;
import com.snapp.expensetracker.services.ExpenseCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ExpenseCategoryController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/expenseCategory")
    public ResponseEntity<ExpenseCategoryDto> createCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto){
        ExpenseCategory expenseCategory = mapper.map(expenseCategoryDto, ExpenseCategory.class);
        ExpenseCategory newExpenseCategory = expenseCategoryService.save(expenseCategory);
        ExpenseCategoryDto newExpenseCategoryDto = mapper.map(newExpenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(newExpenseCategoryDto, HttpStatus.CREATED);
    }
}
