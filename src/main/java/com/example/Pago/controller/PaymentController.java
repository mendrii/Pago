package com.example.Pago.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.validation.annotation.Validated;
import java.util.List;

import com.example.Pago.model.Payment;
import com.example.Pago.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Procesa un nuevo pago
     * @param payment datos del pago a procesar
     * @return pago procesado con timestamp
     */
    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        try {
            Payment processedPayment = paymentService.processPayment(payment);
            return ResponseEntity.ok(processedPayment);
        } catch (Exception e) {
            throw new RuntimeException("Error procesando pago: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los pagos registrados
     * @return lista de pagos
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo pagos: " + e.getMessage());
        }
    }
}
