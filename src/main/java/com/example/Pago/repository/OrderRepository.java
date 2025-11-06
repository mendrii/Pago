package com.example.Pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Pago.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
