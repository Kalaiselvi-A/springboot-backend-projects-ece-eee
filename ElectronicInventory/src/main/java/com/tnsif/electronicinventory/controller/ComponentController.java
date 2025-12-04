package com.tnsif.electronicinventory.controller;

import com.tnsif.electronicinventory.entity.Component;
import com.tnsif.electronicinventory.service.ComponentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/components")
@CrossOrigin(origins = "*")
public class ComponentController {

    private final ComponentService service;

    public ComponentController(ComponentService service) {
        this.service = service;
    }

    // Add new component
    @PostMapping
    public Component addComponent(@Valid @RequestBody Component component) {
        return service.addComponent(component);
    }

    // Get all components
    @GetMapping
    public List<Component> getAllComponents() {
        return service.getAllComponents();
    }

    // Get component by ID
    @GetMapping("/{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable Long id) {
        return service.getComponentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update component
    @PutMapping("/{id}")
    public ResponseEntity<Component> updateComponent(
            @PathVariable Long id,
            @Valid @RequestBody Component component) {

        return service.getComponentById(id)
                .map(existing -> {
                    component.setId(id);
                    return ResponseEntity.ok(service.updateComponent(component));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete component
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Long id) {
        service.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }

    // Search by type
    @GetMapping("/search")
    public List<Component> searchByType(@RequestParam String type) {
        return service.findByType(type);
    }

    // List low stock items
    @GetMapping("/low-stock")
    public List<Component> getLowStock(@RequestParam int threshold) {
        return service.findLowStock(threshold);
    }
}
