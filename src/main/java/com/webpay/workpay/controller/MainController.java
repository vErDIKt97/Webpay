package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Role;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.PayFileRepo;
import com.webpay.workpay.repository.UserRepo;
import com.webpay.workpay.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/userInfo")
public class MainController {
    private final UserService userService;
    private final UserRepo userRepo;
    private final PayFileRepo payFileRepo;

    @Value("${file.sells}")
    private String fileSells;
    public MainController(UserService userService, UserRepo userRepo, PayFileRepo payFileRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.payFileRepo = payFileRepo;
    }

    @GetMapping
    public String userInfo(Model model) {
      User user = userService.getCurrentUser();
      payFileRepo.findByFileName(fileSells).findSells(user);
      userRepo.save(user);
      model.addAttribute("user",user);
      model.addAttribute("admin",Role.ADMIN);
      return "userInfo";
    }

    @GetMapping("/currentUserEdit")
    public String userEdit (Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("login",user.getUsername());
        model.addAttribute("password",user.getPassword());
        return "currentUserEdit";
    }

    @PostMapping("/currentUserEdit")
    public String currentUserEdit (@RequestParam("login")String login,
                                   @RequestParam("password")String password,
                                   Model model) {
        User user = userService.getCurrentUser();
        user.setUsername(login);
        user.setPassword(password);
        userRepo.save(user);
        model.addAttribute("user", user);
        return "userInfo";
    }


}
