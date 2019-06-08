package com.webpay.workpay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class MainController {
    @GetMapping
    String userInfo() {
        return "userInfo";
    }
}
