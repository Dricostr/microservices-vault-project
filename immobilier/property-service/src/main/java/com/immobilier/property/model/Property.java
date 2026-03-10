package com.immobilier.property.model;

import jakarta.persistence.*;
import lombok.Data;

@Data           // Lombok: auto-generates getters, setters, toString, equals
@Entity         // Tells JPA: "map this class to a database table"
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment PK
    private Long id;

    private String title;
    private String description;
    private String location;
    private Double price;
    private String type;      // APARTMENT, HOUSE, COMMERCIAL
    private String status;    // AVAILABLE, SOLD, RENTED
    private Integer rooms;
    private Double surface;   // m²

    @Column(name = "agent_id")
    private Long agentId;     // reference to agent (no FK join across services!)
}