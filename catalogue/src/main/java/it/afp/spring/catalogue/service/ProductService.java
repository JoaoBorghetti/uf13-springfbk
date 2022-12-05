package it.afp.spring.catalogue.service;

import it.afp.spring.catalogue.domain.Product;
import it.afp.spring.catalogue.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProduct(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Product createProduct(Product input) {
        return repository.save(input);
    }

    public Product updateAvailability(String productId, int diff) {
        Product product = repository.findById(productId).orElse(null);
        if (product != null) {
            product.setAvailability(product.getAvailability() - diff);
            return repository.save(product);
        }
        return null;
    }
}
