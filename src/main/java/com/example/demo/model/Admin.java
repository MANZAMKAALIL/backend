package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;
    private String password;

    @Column(nullable = false)
    private String role = "ADMIN";  // Default role as ADMIN

    public Admin(String username, String password, String admin) {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserAccount(Users user) {
    }

    public Admin orElseThrow(Object adminNotFound) {
        return null;
    }
}
