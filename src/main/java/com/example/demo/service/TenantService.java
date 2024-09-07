package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.model.Property;
import com.example.demo.model.Tenant;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, Tenant tenantDetails) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Tenant not found"));
        tenant.setFirstName(tenantDetails.getFirstName());
        tenant.setLastName(tenantDetails.getLastName());
        tenant.setEmail(tenantDetails.getEmail());
        // Update other fields as needed
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Tenant not found"));
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Payment payRent(Long tenantId, BigDecimal amount) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(() -> new RuntimeException("Tenant not found"));

        // Create a new payment record
        Payment payment = new Payment();
        payment.setTenant(tenant);
        payment.setAmount(amount);
        payment.setDate(new java.util.Date());

        // Save the payment record
        return paymentRepository.save(payment);
    }

    public Tenant assignPropertyToTenant(Long tenantId, Long propertyId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(() -> new RuntimeException("Tenant not found"));
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));

        // Assign the property to the tenant
        tenant.setProperty(property);
        return tenantRepository.save(tenant);
    }
}
