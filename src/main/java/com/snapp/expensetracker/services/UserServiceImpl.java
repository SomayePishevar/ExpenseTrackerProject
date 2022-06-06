package com.snapp.expensetracker.services;

import com.snapp.expensetracker.common.RoleEnum;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.repository.JpaUserRepository;
import com.snapp.expensetracker.payload.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JpaUserRepository userRepository;



    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
