package it.afp.spring.purchase.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Order {
    @Id
    private String id;
    private String productId;
    private String title;
    private String description;
    private Double price;
    private Integer quantity;
    private String userId;
}
