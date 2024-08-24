package fcu.web._20240818orderingsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private String productName;
    private Double price;
    private Integer quantity;

    @ManyToOne
    private Order order;

    // No need to explicitly declare getters and setters due to @Data annotation
}