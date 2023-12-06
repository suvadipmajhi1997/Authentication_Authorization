package com.example.springSecurity.controller;

import com.example.springSecurity.entity.User;
import com.example.springSecurity.repository.UserRepo;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    private UserRepo userRepo;

    public UserController() {
    }

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = this.userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

    @GetMapping({"/profile"})
    public String profile() {
        return "profile";
    }
}