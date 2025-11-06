package com.example.Pago.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_order")
public class Order {
    @Id 
    @GeneratedValue
    private Long id;

    private String customerName;
    private LocalDateTime orderDate;
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
    
}
