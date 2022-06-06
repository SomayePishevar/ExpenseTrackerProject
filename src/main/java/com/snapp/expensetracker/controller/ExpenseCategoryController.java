package com.snapp.expensetracker.controller;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.payload.ExpenseCategoryDto;
import com.snapp.expensetracker.services.ExpenseCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/expenseCategory")
    public ResponseEntity<List<ExpenseCategoryDto>> getAllCategory(){
        List<ExpenseCategory> expenseCategoryList = expenseCategoryService.getAllExpenseCategories();
        List<ExpenseCategoryDto> expenseCategoryDtoList = new ArrayList<>();
        for (ExpenseCategory expenseCategory : expenseCategoryList){
            ExpenseCategoryDto expenseCategoryDto = mapper.map(expenseCategory, ExpenseCategoryDto.class);
            expenseCategoryDtoList.add(expenseCategoryDto);
        }
        return new ResponseEntity<>(expenseCategoryDtoList, HttpStatus.OK);
    }
}