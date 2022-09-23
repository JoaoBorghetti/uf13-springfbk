package com.example.purchase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.purchase.domain.Product;

@Service
public class CatalogClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CircuitBreakerFactory factory;
	

	public Product getProduct(String id) {
		CircuitBreaker cb = factory.create("productCircuitBracker");

		return cb.run(() -> {
			return restTemplate.getForObject("http://catalogue/api/products/" + id, Product.class);
		}, err -> {
			return getDefaultProduct(id);
		});
	}
	
	private Product getDefaultProduct(String id) {
		Product product = new Product();
		product.setId(id);
		return product;
	}



	public void changeAvailability(String id, Integer quantity) {
		CircuitBreaker cb = factory.create("AvailabilityCircuitBracker");
		cb.run(() -> {
			return restTemplate.exchange("http://catalogue/api/products/" + id +"/availability/" + quantity, HttpMethod.PUT, null, Product.class);
		}, err -> {
			return null;
		});
	}
}
