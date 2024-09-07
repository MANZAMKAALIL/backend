package com.example.demo.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends Payment {
    private String receiptNumber;

    // Additional attributes or methods specific to cash payments
}

