package com.example.purchase.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.purchase.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

	List<Order> findByUserId(Object userId);

}
