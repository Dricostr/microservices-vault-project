package com.immobilier.property.controller;

import com.immobilier.property.model.Property;
import com.immobilier.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController                  // = @Controller + @ResponseBody (returns JSON)
@RequestMapping("/api/properties")
@RequiredArgsConstructor         // Lombok: injects PropertyRepository via constructor
public class PropertyController {

    private final PropertyRepository propertyRepository;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getById(@PathVariable Long id) {
        return propertyRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Property> search(
        @RequestParam(required = false) String location,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String status) {
        if (location != null) return propertyRepository.findByLocation(location);
        if (type != null)     return propertyRepository.findByType(type);
        if (status != null)   return propertyRepository.findByStatus(status);
        return propertyRepository.findAll();
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(
            @PathVariable Long id, @RequestBody Property updated) {
        return propertyRepository.findById(id).map(p -> {
            p.setTitle(updated.getTitle());
            p.setPrice(updated.getPrice());
            p.setStatus(updated.getStatus());
            p.setLocation(updated.getLocation());
            return ResponseEntity.ok(propertyRepository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}