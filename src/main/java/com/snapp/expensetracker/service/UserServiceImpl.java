package com.snapp.expensetracker.service;

import com.snapp.expensetracker.entity.RoleEnum;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.repository.JpaUserRepository;
import com.snapp.expensetracker.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = User.builder().
                username(userDto.getUsername()).
                password(bCryptPasswordEncoder.encode(userDto.getPassword())).
                confirmPassword(userDto.getConfirmPassword()).
                role(RoleEnum.USER).
                phoneNumber(userDto.getPhoneNumber()).build();
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(UserDto userDto) {
        User user = User.builder().
                username(userDto.getUsername()).
                password(bCryptPasswordEncoder.encode(userDto.getPassword())).
                confirmPassword(userDto.getConfirmPassword()).
                role(RoleEnum.ADMIN).
                phoneNumber(userDto.getPhoneNumber()).build();
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
