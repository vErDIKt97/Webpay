package com.webpay.workpay.controller;

import com.webpay.workpay.domain.User;
import com.webpay.workpay.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
@PreAuthorize("hasAuthority('ADMIN')")
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
    public String addUser (@Valid User user,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
         boolean addUser = userService.addUser(user);
         if (!addUser) {
             model.addAttribute("usernameError","User already exist!");
             return "registration";
         } else
        return "redirect:/user";
    }

}
