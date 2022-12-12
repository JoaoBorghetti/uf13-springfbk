package it.afp.spring.purchase.rest;

import it.afp.spring.purchase.domain.Order;
import it.afp.spring.purchase.service.OrderService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/api/purchase/me")
    public List<Order> purchaseMe(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return orderService.GetUserOrdersFromToken(token);
    }
}
