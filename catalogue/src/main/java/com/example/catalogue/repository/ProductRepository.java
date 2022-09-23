package com.example.catalogue.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.catalogue.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findByCategory(String category);

}
