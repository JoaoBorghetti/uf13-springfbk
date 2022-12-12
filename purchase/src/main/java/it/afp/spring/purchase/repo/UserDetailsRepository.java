package it.afp.spring.purchase.repo;

import it.afp.spring.purchase.domain.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDetailsRepository extends MongoRepository<UserDetails,String> {
    Optional<UserDetails> findByEmail(String email);
}
