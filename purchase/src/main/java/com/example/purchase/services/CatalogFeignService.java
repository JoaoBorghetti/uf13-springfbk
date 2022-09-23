package com.example.purchase.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.purchase.domain.Product;

@FeignClient("catalogue")
public interface CatalogFeignService {

	@GetMapping("/api/products/{id}")
	public Product getProduct(@PathVariable String id);
	@PutMapping("/api/products/{id}/availability/{quantity}")
	public void changeAvailability(@PathVariable String id, @PathVariable Integer quantity);
	
}
