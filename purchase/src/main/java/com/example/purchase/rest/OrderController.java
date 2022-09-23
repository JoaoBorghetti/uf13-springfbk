package com.example.purchase.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purchase.domain.Order;
import com.example.purchase.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/api/purchases/{userId}")
	public List<Order> getUserOrders(@PathVariable String userId) {
		return orderService.getUserOrders(userId);
	}
	
	@GetMapping("/api/purchases/{userId}/{id}")
	public Order getOrder(@PathVariable String userId, @PathVariable String id) {
		return orderService.getOrder(id);
	}
	
	@PostMapping("/api/purchases/{userId}/{productId}/{quantity}")
	public Order purchase(@PathVariable String userId, @PathVariable String productId, @PathVariable Integer quantity) {
		return orderService.createOrder(productId, userId, quantity);
	}
}
