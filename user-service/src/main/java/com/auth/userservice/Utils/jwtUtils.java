package com.auth.userservice.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth.userservice.domain.UserDetails;
import com.auth.userservice.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class jwtUtils {
    @Autowired
    private UserRepository repo;

    public String generateToken(UserDetails details){
        Map<String,String> payload = new HashMap<String,String>();
        payload.put("email",details.email);
        payload.put("id",details.id);
        return JWT.create().withPayload(payload).withIssuedAt(new Date()).sign(Algorithm.HMAC256(details.password));
    }
    
    public Boolean checkTokenValidity(String token, UserDetails details){
        return false;
    }

    public UserDetails getFromToken(String token){
        System.out.println(JWT.decode(token).getClaims().get("id").asString());
        return repo.findById(JWT.decode(token).getClaims().get("id").asString()).orElse(null);
    }
}
