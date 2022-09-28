package main.java.com.auth.rest;

import org.springframework.data.web;
import org.springframework.beans.factory.annotation.Autowired;

import main.java.com.auth.service.AuthService;

@RestController
public class AuthController {
    /*ADD /register AND /login with JWT Auth */
    @Autowired
    AuthService service;

    @PostMapping("/auth/register")
    public void Register(){
        //service.register()
    }

    @PostMapping("/auth/login")
    public void Login(){
        //service.Login()
    }
}
