package com.example.Pago.model;

import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id 
    @GeneratedValue
    private Long id;

    private String method; // e.g. "Credit Card", "PayPal"
    private boolean paid;
    private LocalDateTime paymentDate;
    
}
