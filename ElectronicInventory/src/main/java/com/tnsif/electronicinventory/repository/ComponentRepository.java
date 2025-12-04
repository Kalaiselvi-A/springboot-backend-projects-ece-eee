package com.tnsif.electronicinventory.repository;

import com.tnsif.electronicinventory.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

    // Search components by type (Resistor, Capacitor, IC...)
    List<Component> findByTypeContainingIgnoreCase(String type);

    // Fetch items lower than given stock limit
    List<Component> findByQuantityLessThan(int quantity);
}
