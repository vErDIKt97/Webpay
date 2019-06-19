package com.webpay.workpay.service;

import com.webpay.workpay.domain.Role;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.PayFileRepo;
import com.webpay.workpay.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    private final PayFileRepo payFileRepo;

    @Value("${file.sells")
    private String fileSells;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PayFileRepo payFileRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.payFileRepo = payFileRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser (User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            return false;
        }
        user.setActive(true);
        if (userRepo.findAll().isEmpty())
            user.setRoles(Collections.singleton(Role.ADMIN));
        else
            user.setRoles(Collections.singleton(Role.USER));
        if (payFileRepo.findByFileName(fileSells)!=null)
            payFileRepo.findByFileName(fileSells).findSells(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object obj = authentication.getPrincipal();
        String username = "";
        if (obj instanceof UserDetails) {
            username = ((UserDetails)obj).getUsername();
            User user = userRepo.findByUsername(username);
            return user;
        }
        return null;
    }
}
