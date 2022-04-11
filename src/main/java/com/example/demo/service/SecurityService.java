package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;

    public String addUser(User user) {
        Optional<User> userFromBD = userService.findUserByName(user.getName());
        if (!userFromBD.isPresent()) {
            userService.saveUser(user);
            return "You're signup like " + user.getName() + "\n";
        } else {
            return "User with this email already exist";
        }
    }
}