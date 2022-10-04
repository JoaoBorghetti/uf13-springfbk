package com.auth.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.userservice.Utils.jwtUtils;
import com.auth.userservice.domain.UserDetails;
import com.auth.userservice.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private jwtUtils utils;
    public UserDetails addUser(UserDetails usr){
        return repo.save(usr);
    }

    public UserDetails getByEmail(String email){
        return repo.findByEmail(email).orElse(null);
    }


    public String AuthenticateUser(String email, String password){
        UserDetails details = repo.findByEmail(email).orElse(null);
        if(details != null && password.equals(details.password)){
            return utils.generateToken(details);
        }
        return null;
    };


    public UserDetails getUserFromToken(String token){
        return utils.getFromToken(token);
    }
}
