package com.snapp.expensetracker.web;

import com.snapp.expensetracker.JwtTokenProvider;
import com.snapp.expensetracker.payload.JWTAuthResponse;
import com.snapp.expensetracker.payload.LoginDto;
import com.snapp.expensetracker.services.SecurityService;
import com.snapp.expensetracker.services.UserService;
import com.snapp.expensetracker.validator.UserDataValidator;
import com.snapp.expensetracker.payload.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDataValidator userDataValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }

    @PostMapping("/registration/admin")
    public String adminRegistration(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult){
        userDataValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()){
            return "registration";
        }

        userService.saveAdmin(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome(Model model){
        return "welcome";
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> usthenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
}
