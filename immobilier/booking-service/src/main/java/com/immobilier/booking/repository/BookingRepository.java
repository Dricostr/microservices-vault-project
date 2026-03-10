package com.immobilier.booking.repository;

import com.immobilier.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPropertyId(Long propertyId);
    List<Booking> findByAgentId(Long agentId);
    List<Booking> findByClientEmail(String email);
    List<Booking> findByStatus(String status);
}