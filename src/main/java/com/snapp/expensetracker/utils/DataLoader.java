package com.snapp.expensetracker.utils;

import com.snapp.expensetracker.common.RoleEnum;
import com.snapp.expensetracker.entity.ExpenseCategory;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.repository.JpaExpenseCategoryRepository;
import com.snapp.expensetracker.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private JpaExpenseCategoryRepository categoryRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.save(User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .role(RoleEnum.ADMIN)
                .phoneNumber("+989196263580")
                .build());

        Set<ExpenseCategory> expenseCategoryList = new HashSet<>() ;
        ExpenseCategory foodCategory = ExpenseCategory.builder()
                .name("food")
                .maxExpense(BigInteger.valueOf(1500))
                .build();
        ExpenseCategory transportCategory = ExpenseCategory.builder()
                .name("transport")
                .maxExpense(BigInteger.valueOf(500))
                .build();
        ExpenseCategory clothingCategory = ExpenseCategory.builder()
                .name("clothing")
                .maxExpense(BigInteger.valueOf(700))
                .build();
        expenseCategoryList.add(foodCategory);
        expenseCategoryList.add(transportCategory);
        expenseCategoryList.add(clothingCategory);
        for (ExpenseCategory expenseCategory : expenseCategoryList){
            categoryRepository.save(expenseCategory);
        }
    }
}
