package it.afp.spring.purchase.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id, title, description, category;
    private Integer availability;
    private Double price;
}
