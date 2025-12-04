package com.tnsif.electronicinventory.service;

import com.tnsif.electronicinventory.entity.Component;
import com.tnsif.electronicinventory.repository.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentService {

    private final ComponentRepository repository;

    public ComponentService(ComponentRepository repository) {
        this.repository = repository;
    }

    // Add new component
    public Component addComponent(Component component) {
        return repository.save(component);
    }

    // Get all components
    public List<Component> getAllComponents() {
        return repository.findAll();
    }

    // Get component by ID
    public Optional<Component> getComponentById(Long id) {
        return repository.findById(id);
    }

    // Update component
    public Component updateComponent(Component component) {
        return repository.save(component);
    }

    // Delete component
    public void deleteComponent(Long id) {
        repository.deleteById(id);
    }

    // Search by type (Resistor, Capacitor, IC…)
    public List<Component> findByType(String type) {
        return repository.findByTypeContainingIgnoreCase(type);
    }

    // List low-stock components  
    public List<Component> findLowStock(int threshold) {
        return repository.findByQuantityLessThan(threshold);
    }
}
