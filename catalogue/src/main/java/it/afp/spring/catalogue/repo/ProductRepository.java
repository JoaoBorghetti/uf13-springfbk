package it.afp.spring.catalogue.repo;

import it.afp.spring.catalogue.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByCategory(String category);
}
