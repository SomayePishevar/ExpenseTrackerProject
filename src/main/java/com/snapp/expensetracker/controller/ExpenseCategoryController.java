package com.snapp.expensetracker.controller;

import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.payload.ExpenseCategoryDto;
import com.snapp.expensetracker.services.ExpenseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Expense Category CRUD")
public class ExpenseCategoryController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;


    @ApiOperation(value = "This method is used to add a new expense category.")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/expenseCategories")
    public ResponseEntity<ExpenseCategoryDto> createCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto){
        ExpenseCategory expenseCategory = mapper.map(expenseCategoryDto, ExpenseCategory.class);
        ExpenseCategory newExpenseCategory = expenseCategoryService.save(expenseCategory);
        ExpenseCategoryDto newExpenseCategoryDto = mapper.map(newExpenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(newExpenseCategoryDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "This method is used to get all expense categories.")
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @ApiOperation(value = "This method is used to get expense category by Id.")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/expenseCategories/{id}")
    public ResponseEntity<ExpenseCategoryDto> getCategoryById(@PathVariable("id") long id){
        ExpenseCategory expenseCategory = expenseCategoryService.findById(id);
        ExpenseCategoryDto expenseCategoryDto = mapper.map(expenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(expenseCategoryDto, HttpStatus.OK);
    }

    @ApiOperation(value = "This method is used to update expense category by its Id.")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/expenseCategories/{id}")
    public ResponseEntity<ExpenseCategoryDto> updateCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto, @PathVariable("id") long id){
        ExpenseCategory expenseCategory = mapper.map(expenseCategoryDto, ExpenseCategory.class);
        ExpenseCategory updatedExpenseCategory = expenseCategoryService.update(expenseCategory, id);
        ExpenseCategoryDto updatedExpenseCategoryDto = mapper.map(updatedExpenseCategory, ExpenseCategoryDto.class);
        return new ResponseEntity<>(updatedExpenseCategoryDto, HttpStatus.OK);
    }

    @ApiOperation(value = "This method is used to delete expense category by its Id.")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/expenseCategories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id){
        expenseCategoryService.deleteById(id);
        return new ResponseEntity<>("category deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "This method is used to get the name of all expense categories.")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/expenseCategories/names")
    public ResponseEntity<List<String>> getAllCategoryNames(){
        List<String> expenseCategoryNameList = expenseCategoryService.getAllExpenseCategoryName();
        return new ResponseEntity<>(expenseCategoryNameList, HttpStatus.OK);
    }

}
