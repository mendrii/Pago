package com.example.Pago.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Pago.dto.NotificationRequest;

/**
 * Cliente Feign para comunicarse con el servicio de notificaciones
 * Reemplaza la URL base según tu configuración de servicios
 */
@FeignClient(
    name = "notification-service",
    url = "${notification.service.url:http://localhost:8081}"
)
public interface NotificationClient {

    /**
     * Envía una notificación al servicio de notificaciones
     * @param request datos de la notificación
     * @return respuesta del servicio
     */
    @PostMapping("/api/notifications/send")
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);

    /**
     * DTO para la respuesta de notificación
     */
    class NotificationResponse {
        public boolean success;
        public String message;
        public Long notificationId;

        public NotificationResponse() {}

        public NotificationResponse(boolean success, String message, Long notificationId) {
            this.success = success;
            this.message = message;
            this.notificationId = notificationId;
        }
    }
}
