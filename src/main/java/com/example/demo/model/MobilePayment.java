package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
//@DiscriminatorValue("MOBILE")
public class MobilePayment   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mobilePaymentId;
    private String mobileNumber;
    private String providerName;

    // Additional attributes or methods specific to mobile payments
}

