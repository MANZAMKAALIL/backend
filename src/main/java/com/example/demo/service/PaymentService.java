package com.example.demo.service;

import com.example.demo.model.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    public Payment createMobilePayment(Long bookingId, String mobileNumber, String providerName, BigDecimal amount) {
        // Implementation of the mobile payment creation
        return null; // Replace with actual implementation
    }

    public Payment createCashPayment(Long bookingId, String receiptNumber, BigDecimal amount) {
        // Implementation of the cash payment creation
        return null; // Replace with actual implementation
    }
}
