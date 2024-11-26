package com.security.SecurityEx.controller;

import com.security.SecurityEx.model.Users;
import com.security.SecurityEx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registeruser")
    public Users registerUser(@RequestBody Users user){
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verifyUser(user);
    }
}
