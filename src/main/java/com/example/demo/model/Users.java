package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // "ADMIN", "AGENT", "TENANT", "CUSTOMER"

    // Custom methods can be added if needed

    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }




    // Other methods are handled by Lombok (@Data generates getters, setters, toString, equals, and hashcode)
}
