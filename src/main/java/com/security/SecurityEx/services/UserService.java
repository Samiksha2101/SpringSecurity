package com.security.SecurityEx.services;

import com.security.SecurityEx.model.Users;
import com.security.SecurityEx.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtservice;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users saveUser(Users user){
        user.setPassword(
                encoder.encode(user.getPassword())); //to encode the password
        return userRepo.save(user);
    }

    public String verifyUser(Users user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtservice.generateToken(user.getUsername());
        return "fail";
    }
}