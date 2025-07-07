package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<String> savePayment(@RequestBody Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return ResponseEntity.ok("Payment saved successfully");
    }
}
