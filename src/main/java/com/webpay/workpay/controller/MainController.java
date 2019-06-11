package com.webpay.workpay.controller;

import com.webpay.workpay.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class MainController {

    private final UserRepo userRepo;

    public MainController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    String userInfo() {


        return "userInfo";
    }
}
