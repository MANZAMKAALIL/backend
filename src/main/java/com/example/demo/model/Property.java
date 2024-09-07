package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@Setter
@Getter
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private PropertyType propertyType;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "agent_id")
    private Long agentId;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PropertyImage> images;

    // New fields for 3D tour and location coordinates
    private String virtualTourUrl;
    private Double latitude;
    private Double longitude;

    private String location;
    private String status;

    // Constructors
    public Property() {}

    public Property(Long id) {
        this.id = id;
    }

    public Property(String name, String description, BigDecimal price, PropertyType propertyType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.propertyType = propertyType;
    }

    // PropertyType enum defined within the same file
    public enum PropertyType {
        APARTMENT,
        HOUSE,
        COMMERCIAL,
        LAND,
        OTHER
    }
}
