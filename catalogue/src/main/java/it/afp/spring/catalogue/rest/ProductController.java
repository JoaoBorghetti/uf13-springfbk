package it.afp.spring.catalogue.rest;

import it.afp.spring.catalogue.domain.Product;
import it.afp.spring.catalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable String id) { return service.getProduct(id); }

    @GetMapping("/api/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return service.findByCategory(category);

    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }
    @PutMapping("/api/products/{id}/availability/{diff}")
    public Product changeAvailability(@PathVariable String id, @PathVariable Integer diff) {
        return service.updateAvailability(id, diff);
    }
}