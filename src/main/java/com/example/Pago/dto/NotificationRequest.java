package com.example.Pago.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private String to;
    private String subject;
    private String message;
    private String type; // EMAIL, SMS, PUSH
    private Long relatedOrderId;
}
