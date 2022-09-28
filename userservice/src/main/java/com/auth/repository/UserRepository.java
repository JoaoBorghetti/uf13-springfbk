package main.java.com.auth.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import main.java.com.auth.domain.UserDetails;

public interface UserRepository extends MongoRepository<UserDetails, String> {
    
}