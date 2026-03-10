package com.immobilier.booking.controller;

import com.immobilier.booking.model.Booking;
import com.immobilier.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        return bookingRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/property/{propertyId}")
    public List<Booking> getByProperty(@PathVariable Long propertyId) {
        return bookingRepository.findByPropertyId(propertyId);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        booking.setStatus("PENDING");
        return bookingRepository.save(booking);
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirm(@PathVariable Long id) {
        return bookingRepository.findById(id).map(b -> {
            b.setStatus("CONFIRMED");
            return ResponseEntity.ok(bookingRepository.save(b));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancel(@PathVariable Long id) {
        return bookingRepository.findById(id).map(b -> {
            b.setStatus("CANCELLED");
            return ResponseEntity.ok(bookingRepository.save(b));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}