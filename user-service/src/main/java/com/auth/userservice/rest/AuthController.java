package com.auth.userservice.rest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.userservice.domain.LoginDetails;
import com.auth.userservice.domain.UserDetails;
import com.auth.userservice.service.UserService;

@RestController
public class AuthController {
    @Autowired
    UserService service;
    @PostMapping("/auth/register")
    public UserDetails Register(@RequestBody UserDetails usr){
        return service.addUser(usr);
    }

    @PostMapping("/auth/login")
    public String Login(@RequestBody LoginDetails details){
        return service.AuthenticateUser(details.email, details.password);
    }

    @PostMapping("/auth/verify")
    public UserDetails Verify(@RequestBody String jwtToken){
        return service.getUserFromToken(jwtToken);
    }

}
