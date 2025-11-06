package com.example.Pago.model;

import java.math.BigDecimal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id 
    @GeneratedValue
    private Long id;

    private String productName;
    private int quantity;
    private BigDecimal price;
    
}
