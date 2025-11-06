package com.example.Pago.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Pago.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}