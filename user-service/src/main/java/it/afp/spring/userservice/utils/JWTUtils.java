package it.afp.spring.userservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import it.afp.spring.userservice.domain.UserDetails;
import it.afp.spring.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    @Autowired
    private UserRepository repo;

    /**
     * Generate a token from {@link UserDetails user}. It encodes the user's email and id and sign it with the user's password.
     * @param details {@link UserDetails} user's details.
     * @return JWT generated from the user's email and id.
     */
    public String generateToken(UserDetails details){
        Map<String,String> payload = new HashMap<String,String>();
        payload.put("email",details.email);
        payload.put("id",details.id);
        return JWT.create().withPayload(payload).withIssuedAt(new Date()).sign(Algorithm.HMAC256(details.password));
    }

    /**
     * decode the token and obtain the user's id.
     * @param token Json Web Token of the user.
     * @return the user's id
     */
    public UserDetails getFromToken(String token){
        try{
            return repo.findById(JWT.decode(token).getClaims().get("id").asString()).orElse(null);
        }catch (Exception e){
            return null;
        }
    }
}
