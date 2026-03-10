package com.immobilier.booking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_id", nullable = false)
    private Long propertyId;    // reference to property service

    @Column(name = "agent_id")
    private Long agentId;       // reference to agent service

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_email", nullable = false)
    private String clientEmail;

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;

    private String status;      // PENDING, CONFIRMED, CANCELLED

    private String notes;
}