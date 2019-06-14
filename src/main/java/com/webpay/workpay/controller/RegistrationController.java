package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Role;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.PayFileRepo;
import com.webpay.workpay.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
//@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {
    private final UserRepo userRepo;
    private final PayFileRepo payFileRepo;
    public RegistrationController(UserRepo userRepo, PayFileRepo payFileRepo) {
        this.userRepo = userRepo;
        this.payFileRepo = payFileRepo;
    }

    @Value("${file.sells}")
    private String fileSells;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser (@RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           User user, Map<String ,Object> model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            model.put("message","User exist!");
            return "registration";
        }
        user.setActive(true);
        if (userRepo.findAll().isEmpty())
        user.setRoles(Collections.singleton(Role.ADMIN));
        else
            user.setRoles(Collections.singleton(Role.USER));
        user.setName(name);
        user.setSurname(surname);
        if (payFileRepo.findByFileName(fileSells)!=null)
            payFileRepo.findByFileName(fileSells).findSells(user);
        userRepo.save(user);
        return "redirect:/user";
    }

}
