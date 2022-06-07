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
@RequestMapping("/api/adminOperation")
public class ExpenseCategoryController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/expenseCategories")
    public ResponseEntity<ExpenseCategoryDto> createCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto){
        ExpenseCategory expenseCategory = mapper.map(expenseCategoryDto, ExpenseCategory.class);
        ExpenseCategory newExpenseCategory = expenseCategoryService.save(expenseCategory);
        ExpenseCategoryDto newExpenseCategoryDto = mapper.map(newExpenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(newExpenseCategoryDto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/expenseCategories")
    public ResponseEntity<List<ExpenseCategoryDto>> getAllCategory(){
        List<ExpenseCategory> expenseCategoryList = expenseCategoryService.getAllExpenseCategories();
        List<ExpenseCategoryDto> expenseCategoryDtoList = new ArrayList<>();
        for (ExpenseCategory expenseCategory : expenseCategoryList){
            ExpenseCategoryDto expenseCategoryDto = mapper.map(expenseCategory, ExpenseCategoryDto.class);
            expenseCategoryDtoList.add(expenseCategoryDto);
        }
        return new ResponseEntity<>(expenseCategoryDtoList, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/expenseCategories/{id}")
    public ResponseEntity<ExpenseCategoryDto> getCategoryById(@PathVariable("id") long id){
        ExpenseCategory expenseCategory = expenseCategoryService.findById(id);
        ExpenseCategoryDto expenseCategoryDto = mapper.map(expenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(expenseCategoryDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/expenseCategories/{id}")
    public ResponseEntity<ExpenseCategoryDto> updateCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto, @PathVariable("id") long id){
        ExpenseCategory expenseCategory = mapper.map(expenseCategoryDto, ExpenseCategory.class);
        ExpenseCategory updatedExpenseCategory = expenseCategoryService.update(expenseCategory, id);
        ExpenseCategoryDto updatedExpenseCategoryDto = mapper.map(updatedExpenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(updatedExpenseCategoryDto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/expenseCategories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id){
        expenseCategoryService.deleteById(id);
        return new ResponseEntity<>("category deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/expenseCategories/names")
    public ResponseEntity<List<String>> getAllCategoryNames(){
        List<String> expenseCategoryNameList = expenseCategoryService.getAllExpenseCategoryName();
        return new ResponseEntity<>(expenseCategoryNameList, HttpStatus.OK);
    }

}
