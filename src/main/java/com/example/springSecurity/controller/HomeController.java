package com.example.springSecurity.controller;

import com.example.springSecurity.entity.User;
import com.example.springSecurity.repository.UserRepo;
import com.example.springSecurity.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;


    public HomeController() {
    }

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = this.userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

    @GetMapping({"/"})
    public String index() {
        return "index";
    }

    @GetMapping({"/register"})
    public String register() {
        return "register";
    }

    @GetMapping({"/signin"})
    public String login() {
        return "login";
    }

    @PostMapping({"/saveUser"})
    public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {
        User u = this.userService.saveUser(user);
        if (u != null) {
            session.setAttribute("msg", "Register successfully");
        } else {
            session.setAttribute("msg", "Something wrong server");
        }

        return "redirect:/register";
    }
}