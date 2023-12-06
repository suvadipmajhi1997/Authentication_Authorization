package com.example.springSecurity.config;

import com.example.springSecurity.entity.User;
import com.example.springSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public CustomUserDetailsService() {
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = this.userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        } else {
            return new CustomUser(user);
        }
    }
}
