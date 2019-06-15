package com.webpay.workpay.controller;

import com.webpay.workpay.domain.User;
import com.webpay.workpay.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
//@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser (User user, Model model) {
         boolean addUser = userService.addUser(user);
         if (!addUser) {
             model.addAttribute("message","User already exist!");
             return "registration";
         } else
        return "redirect:/user";
    }

}
