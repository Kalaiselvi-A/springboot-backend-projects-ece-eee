package com.tnsif.energy.controller;

import com.tnsif.energy.entity.EnergyMeter;
import com.tnsif.energy.service.EnergyMeterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meters")
@CrossOrigin("*")
public class EnergyMeterController {

    private final EnergyMeterService service;

    public EnergyMeterController(EnergyMeterService service) {
        this.service = service;
    }

    // ------------------- CRUD -----------------------

    // Create new meter
    @PostMapping
    public ResponseEntity<EnergyMeter> createMeter(@RequestBody EnergyMeter meter) {
        return ResponseEntity.ok(service.saveMeter(meter));
    }

    // Get all meters
    @GetMapping
    public ResponseEntity<List<EnergyMeter>> getAllMeters() {
        return ResponseEntity.ok(service.getAllMeters());
    }

    // Get meter by ID
    @GetMapping("/{id}")
    public ResponseEntity<EnergyMeter> getMeterById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMeterById(id));
    }

    // Update meter
    @PutMapping("/{id}")
    public ResponseEntity<EnergyMeter> updateMeter(@PathVariable Long id, @RequestBody EnergyMeter meter) {
        return ResponseEntity.ok(service.updateMeter(id, meter));
    }

    // Delete meter
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeter(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteMeter(id));
    }

    // ------------------- Get by Meter ID -----------------------

    @GetMapping("/meterId/{meterId}")
    public ResponseEntity<EnergyMeter> getMeterByMeterId(@PathVariable String meterId) {
        return ResponseEntity.ok(service.getMeterByMeterId(meterId));
    }
}
