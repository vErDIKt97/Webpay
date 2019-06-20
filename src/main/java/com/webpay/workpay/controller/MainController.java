package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Role;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.PayFileRepo;
import com.webpay.workpay.repository.UserRepo;
import com.webpay.workpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/userInfo")
public class MainController {
    private final UserService userService;
    private final UserRepo userRepo;
    private final PayFileRepo payFileRepo;

    private final PasswordEncoder passwordEncoder;

    @Value("${file.sells}")
    private String fileSells;
    public MainController(UserService userService, UserRepo userRepo, PayFileRepo payFileRepo, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.payFileRepo = payFileRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String userInfo(Model model) {
      User user = userService.getCurrentUser();
      if (!payFileRepo.findAll().isEmpty())
      payFileRepo.findByFileName(fileSells).findSells(user);
      userRepo.save(user);
      model.addAttribute("encoder",passwordEncoder);
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
    public String currentUserEdit (@Valid User user,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "currentUserEdit";
        }

        User currentUser = userService.getCurrentUser();
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(currentUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("userActive", currentUser.isActive());
        return "index";
    }


}
