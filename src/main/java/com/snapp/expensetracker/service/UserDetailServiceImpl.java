package com.snapp.expensetracker.service;

import com.snapp.expensetracker.domain.entity.Role;
import com.snapp.expensetracker.domain.entity.User;
import com.snapp.expensetracker.domain.repository.JpaUserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), grantedAuthorities);
    }
}
