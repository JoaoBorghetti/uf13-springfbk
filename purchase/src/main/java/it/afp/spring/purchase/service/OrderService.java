package it.afp.spring.purchase.service;

import it.afp.spring.purchase.domain.Order;
import it.afp.spring.purchase.domain.Product;
import it.afp.spring.purchase.domain.UserDetails;
import it.afp.spring.purchase.repo.OrderRepository;
import it.afp.spring.purchase.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderService {
    final Logger logger = Logger.getLogger(OrderService.class.getName());
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private UserDetailsRepository userRepo;
    @Autowired
    private CatalogFeignService clientService;
    @Autowired
    private UserServiceFeignService authService;

    public List<Order> getUserOrders(String userId) {
        return orderRepo.findByUserId(userId);
    }

    public Order getOrder(String id) {
        return orderRepo.findById(id).orElse(null);
    }

    /**
     * Create an order if user's id is valid, product is valid and quantity is valid
     * @param productId
     * @param userId
     * @param quantity
     * @return Order if successful, otherwise null.
     */
    public Order createOrder(String productId, String userId, Integer quantity) {
        UserDetails user = userRepo.findById(userId).orElse(null);
        if(user == null){
            return null;
        }
        Product product = clientService.getProduct(productId);
        if(product == null){
            return null;
        }
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setUserId(userId);
        order.setDescription(product.getDescription());
        order.setTitle(product.getTitle());
        order.setPrice(product.getPrice());
        clientService.changeAvailability(productId, quantity);
        return orderRepo.save(order);
    }

    public List<Order>  GetUserOrdersFromToken(String token) {
        UserDetails user = authService.Verify(token);
        if(user != null){
            return orderRepo.findByUserId(user.id);
        }
        return null;
    }
}
