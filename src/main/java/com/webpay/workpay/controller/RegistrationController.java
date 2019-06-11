package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Role;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {
    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser (@RequestParam("name") String name,
                           @RequestParam("soname") String soname,
                           User user, Map<String ,Object> model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            model.put("message","User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setName(name);
        user.setSoname(soname);
        user.findSells();
        userRepo.save(user);
        return "redirect:/user";
    }

}
