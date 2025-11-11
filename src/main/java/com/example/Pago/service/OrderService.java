package com.example.Pago.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Pago.repository.OrderRepository;
import com.example.Pago.model.Order;
import com.example.Pago.client.NotificationClient;
import com.example.Pago.dto.NotificationRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired(required = false)
    private NotificationClient notificationClient;

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        
        // Enviar notificación al microservicio de notificaciones
        try {
            if (notificationClient != null) {
                NotificationRequest notification = new NotificationRequest();
                notification.setTo(order.getCustomerName());
                notification.setSubject("Orden Creada");
                notification.setMessage("Tu orden #" + savedOrder.getId() + " ha sido creada exitosamente");
                notification.setType("EMAIL");
                notification.setRelatedOrderId(savedOrder.getId());
                
                NotificationClient.NotificationResponse response = notificationClient.sendNotification(notification);
                logger.info("Notificación enviada: {}", response.message);
            }
        } catch (Exception e) {
            logger.warn("Error enviando notificación al crear orden: {}", e.getMessage());
            // No lanzar excepción, la orden se crea igualmente
        }
        
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
