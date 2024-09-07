package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import com.example.demo.service.AuthService;
import com.example.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    // General User Registration (Non-Admin)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        // Disallow certain roles from being registered through this endpoint
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.status(403).body("Unauthorized role registration!");
        }
        authService.registerUser(user);  // Save to Users table
        return ResponseEntity.ok("User registered successfully");
    }

    // Admin Registration
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        authService.registerAdmin(admin);  // Save to Admin table
        return ResponseEntity.ok("Admin registered successfully");
    }

    // Agent Registration (Only Admin can do this)
    @PostMapping("/register/agent")
    public ResponseEntity<?> registerAgent(@RequestBody Users agent, @RequestHeader("Authorization") String token) {
        // Check if the user performing this action is an Admin
        if (!"ADMIN".equals(jwtUtil.extractClaims(token.substring(7)).get("role"))) {
            return ResponseEntity.status(403).body("Only Admin can register an Agent!");
        }
        authService.registerUser(agent);  // Save to Users table
        return ResponseEntity.ok("Agent registered successfully");
    }

    // Tenant Registration (Only Admin can do this)
    @PostMapping("/register/tenant")
    public ResponseEntity<?> registerTenant(@RequestBody Tenant tenant, @RequestHeader("Authorization") String token) {
        // Check if the user performing this action is an Admin
        if (!"ADMIN".equals(jwtUtil.extractClaims(token.substring(7)).get("role"))) {

            return ResponseEntity.status(403).body("Only Admin can register a Tenant!");
        }

        authService.registerTenant  ( tenant);  // Save to Users table
        return ResponseEntity.ok("Tenant registered successfully");
    }

//     User Login (General Users and Admin)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        String jwtToken = authService.authenticateUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(new com.example.demo.security.JwtResponse(jwtToken));
    }
    @PostMapping("/login/tenant")
    public ResponseEntity<?> login(@RequestBody Tenant tenant) {
        String jwtToken = authService.authenticateTenant(tenant.getFirstName(), tenant.getPassword());
        return ResponseEntity.ok(new com.example.demo.security.JwtResponse(jwtToken));
    }


    // Admin Login (Optional, if you want separate login for Admins)
    @PostMapping("/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) {
        // Authenticate Admin and generate JWT token
        String jwtToken = authService.authenticateAdmin(admin.getUsername(), admin.getPassword());
        System.out.println( jwtToken);
        System.out.println( admin.getUsername());
        System.out.println( admin.getPassword());

        return ResponseEntity.ok(new com.example.demo.security.JwtResponse(jwtToken));

    }
}
