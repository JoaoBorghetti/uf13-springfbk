package it.afp.spring.catalogue.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Product {
    @Id private String id;
    private String title;
    private String description;
    private Double price;
    private Integer availability;
    private String category;
}