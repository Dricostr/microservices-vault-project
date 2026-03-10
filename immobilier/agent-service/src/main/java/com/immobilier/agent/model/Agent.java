package com.immobilier.agent.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String agency;
    private Double commissionRate;   // e.g. 0.03 = 3%
    private String specialization;  // RESIDENTIAL, COMMERCIAL, LUXURY
    private Boolean active;
}