package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Role;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.PayFileRepo;
import com.webpay.workpay.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserRepo userRepo;
    private final PayFileRepo payFileRepo;
    public UserController(UserRepo userRepo, PayFileRepo payFileRepo) {
        this.userRepo = userRepo;
        this.payFileRepo = payFileRepo;
    }

    @Value("${file.sells}")
    private String fileSells;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        for (User user :
                userRepo.findAll()) {
            if (payFileRepo.findByFileName(fileSells)!=null)
            payFileRepo.findByFileName(fileSells).findSells(user);
            userRepo.save(user);
        }
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @GetMapping("del{user}")
    public String userDelete(@PathVariable User user, Model model) {
        if (userRepo.findAll().size()!=1) {
            try {
                userRepo.findByUsername(user.getUsername());
                userRepo.delete(user);
                model.addAttribute("users", userRepo.findAll());
                return "userList";
            } catch (Exception e) {
                model.addAttribute("users", userRepo.findAll());
                return "userList";
            }
        } else {
            model.addAttribute("message","You can't del last user");
            model.addAttribute("users", userRepo.findAll());
            return "redirect:/user";
        }
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String password,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);
        user.setSurname(surname);
        user.setName(name);
        user.setPassword(password);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        payFileRepo.findByFileName(fileSells).findSells(user);
        for (String key :
                form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);

        return "redirect:/user";
    }
}
