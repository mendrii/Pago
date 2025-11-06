package com.example.Pago.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Pago.repository.OrderRepository;
import com.example.Pago.model.Order;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
