package it.afp.spring.purchase.service;

import it.afp.spring.purchase.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("catalogue")
public interface CatalogFeignService {
    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable String id);
    @PutMapping("/api/products/{id}/availability/{quantity}")
    public void changeAvailability(@PathVariable String id, @PathVariable Integer quantity);
}
