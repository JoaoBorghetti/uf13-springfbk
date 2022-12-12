package it.afp.spring.purchase.repo;

import it.afp.spring.purchase.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByUserId(Object userId);
}
