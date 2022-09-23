package com.example.purchase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.purchase.domain.Order;
import com.example.purchase.domain.Product;
import com.example.purchase.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	@Autowired
//	private CatalogClientService clientService;
	private CatalogFeignService clientService;
	
	public List<Order> getUserOrders(String userId) {
		return repository.findByUserId(userId);
	}
	
	public Order getOrder(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public Order createOrder(String productId, String userId, Integer quantity) {
		Order order = new Order();
		order.setProductId(productId);
		order.setQuantity(quantity);
		order.setUserId(userId);
		
		Product product = clientService.getProduct(productId);
		order.setDescription(product.getDescription());
		order.setTitle(product.getTitle());
		order.setPrice(product.getPrice());
		
		clientService.changeAvailability(productId, quantity);
		
		return repository.save(order);
	} 
}
