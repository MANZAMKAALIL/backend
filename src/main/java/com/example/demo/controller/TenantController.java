package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.model.Property;
import com.example.demo.model.Tenant;
import com.example.demo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
        return ResponseEntity.ok(tenantService.createTenant(tenant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @RequestBody Tenant tenantDetails) {
        return ResponseEntity.ok(tenantService.updateTenant(id, tenantDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Long id) {
        return ResponseEntity.ok(tenantService.getTenantById(id));
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    @PostMapping("/{id}/pay-rent")
    public ResponseEntity<Payment> payRent(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(tenantService.payRent(id, amount));
    }

    @PostMapping("/{tenantId}/assign-property/{propertyId}")
    public ResponseEntity<Tenant> assignProperty(@PathVariable Long tenantId, @PathVariable Long propertyId) {
        Tenant updatedTenant = tenantService.assignPropertyToTenant(tenantId, propertyId);
        return ResponseEntity.ok(updatedTenant);
    }
}
