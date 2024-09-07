package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
    @RequestMapping("/api/payments")
    public class PaymentController {

        private final PaymentService paymentService;

        public PaymentController(PaymentService paymentService) {
            this.paymentService = paymentService;
        }

        @PostMapping("/mobile")
        public ResponseEntity<Payment> createMobilePayment(@RequestParam Long bookingId,
                                                           @RequestParam String mobileNumber,
                                                           @RequestParam String providerName,
                                                           @RequestParam BigDecimal amount) {
            return ResponseEntity.ok(paymentService.createMobilePayment(bookingId, mobileNumber, providerName, amount));
        }

        @PostMapping("/cash")
        public ResponseEntity<Payment> createCashPayment(@RequestParam Long bookingId,
                                                         @RequestParam String receiptNumber,
                                                         @RequestParam BigDecimal amount) {
            return ResponseEntity.ok(paymentService.createCashPayment(bookingId, receiptNumber, amount));
        }
    }

