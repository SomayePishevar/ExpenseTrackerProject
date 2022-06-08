package com.snapp.expensetracker.controller;

import com.snapp.expensetracker.security.JwtTokenProvider;
import com.snapp.expensetracker.common.RoleEnum;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.payload.JWTAuthResponse;
import com.snapp.expensetracker.payload.LoginDto;
import com.snapp.expensetracker.payload.SignUpDto;
import com.snapp.expensetracker.services.UserService;
import com.snapp.expensetracker.validator.UserDataValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "User Authentication")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDataValidator userDataValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation(value = "This method is used to authenticate user for login operation.")
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @ApiOperation(value = "This method is used to sign up user to application.")
    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody SignUpDto signUpDto){

        userDataValidator.validateSignUpData(signUpDto);
        User user = User.builder()
                .username(signUpDto.getUsername())
                .password(bCryptPasswordEncoder.encode(signUpDto.getPassword()))
                .phoneNumber(signUpDto.getPhoneNumber())
                .role(RoleEnum.USER)
                .build();
        userService.saveUser(user);
        return new ResponseEntity<>("User registered successfully" , HttpStatus.OK);
    }
}
