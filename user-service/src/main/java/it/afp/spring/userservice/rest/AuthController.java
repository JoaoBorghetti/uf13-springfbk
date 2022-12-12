package it.afp.spring.userservice.rest;

import it.afp.spring.userservice.domain.LoginDetails;
import it.afp.spring.userservice.domain.UserDetails;
import it.afp.spring.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
    public UserDetails Verify(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken){
        System.out.println("token: "+jwtToken);
        return service.getUserFromToken(jwtToken);
    }

}
