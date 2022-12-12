package it.afp.spring.userservice.repo;

import it.afp.spring.userservice.domain.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDetails,String> {
    Optional<UserDetails> findByEmail(String email);
}
