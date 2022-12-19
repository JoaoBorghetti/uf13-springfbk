package it.afp.spring.userservice.service;

import it.afp.spring.userservice.domain.UserDetails;
import it.afp.spring.userservice.repo.UserRepository;
import it.afp.spring.userservice.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private JWTUtils utils;
    public UserDetails addUser(UserDetails usr){
        return repo.save(usr);
    }

    public UserDetails getByEmail(String email){
        return repo.findByEmail(email).orElse(null);
    }

    /**
     * Checks if email and password match with a user, then returns a Json Web Token from the user details.
     * @param email email of the user
     * @param password password of the user
     * @return JWT which encodes the user email and id
     */
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
