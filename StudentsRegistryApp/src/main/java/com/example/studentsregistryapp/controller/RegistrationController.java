package com.example.studentsregistryapp.controller;

import com.example.studentsregistryapp.entity.User;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model) {
        var user = new User();

        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user) {
        try {
            userService.saveUser(user);
        }
        catch (StudentsRegistryAppException exception) {
            log.debug(exception.getMessage());
        }
        return "login";
    }

}
