package com.auth.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.auth.userservice.domain.UserDetails;

public interface UserRepository extends MongoRepository<UserDetails,String> {
    Optional<UserDetails> findByEmail(String email);
}
