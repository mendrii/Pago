package com.example.Pago.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Pago.model.Payment;
import com.example.Pago.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        payment.setPaid(true);
        payment.setPaymentDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }


}
