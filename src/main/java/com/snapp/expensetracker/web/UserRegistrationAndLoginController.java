package com.snapp.expensetracker.web;

import com.snapp.expensetracker.services.SecurityService;
import com.snapp.expensetracker.services.UserService;
import com.snapp.expensetracker.validator.UserDataValidator;
import com.snapp.expensetracker.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserRegistrationAndLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDataValidator userDataValidator;

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
}
