package com.immobilier.property.repository;

import com.immobilier.property.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository gives you save(), findById(), findAll(), delete() for FREE
// You just declare method names and Spring writes the SQL automatically!
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLocation(String location);
    List<Property> findByType(String type);
    List<Property> findByStatus(String status);
    List<Property> findByPriceBetween(Double min, Double max);
}