package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @JsonIgnore
    private Property property; // Property can be null initially

    // Constructor for Tenant creation with essential fields
    public Tenant(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // Constructor for authentication-related operations
    public Tenant(String firstName, String password) {
        this.firstName = firstName;
        this.password = password;
    }
}
