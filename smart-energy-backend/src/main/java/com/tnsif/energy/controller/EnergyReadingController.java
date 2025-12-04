package com.tnsif.energy.controller;

import com.tnsif.energy.entity.EnergyReading;
import com.tnsif.energy.service.EnergyReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/readings")
@CrossOrigin("*")
public class EnergyReadingController {

    private final EnergyReadingService service;

    public EnergyReadingController(EnergyReadingService service) {
        this.service = service;
    }

    // ------------------- CRUD -----------------------

    // Create new reading
    @PostMapping
    public ResponseEntity<EnergyReading> createReading(@RequestBody EnergyReading reading) {
        return ResponseEntity.ok(service.saveReading(reading));
    }

    // Get all readings
    @GetMapping
    public ResponseEntity<List<EnergyReading>> getAllReadings() {
        return ResponseEntity.ok(service.getAllReadings());
    }

    // Get reading by ID
    @GetMapping("/{id}")
    public ResponseEntity<EnergyReading> getReadingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReadingById(id));
    }

    // Update reading
    @PutMapping("/{id}")
    public ResponseEntity<EnergyReading> updateReading(@PathVariable Long id, @RequestBody EnergyReading reading) {
        return ResponseEntity.ok(service.updateReading(id, reading));
    }

    // Delete reading
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReading(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteReading(id));
    }

    // ------------------- Daily Usage -----------------------

    @GetMapping("/daily/{meterId}/{date}")
    public ResponseEntity<Double> getDailyUsage(
            @PathVariable String meterId,
            @PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date); // format: YYYY-MM-DD
        return ResponseEntity.ok(service.getDailyUsage(meterId, parsedDate));
    }

    // ------------------- Monthly Usage -----------------------

    @GetMapping("/monthly/{meterId}/{year}/{month}")
    public ResponseEntity<Double> getMonthlyUsage(
            @PathVariable String meterId,
            @PathVariable int year,
            @PathVariable int month) {
        return ResponseEntity.ok(service.getMonthlyUsage(meterId, year, month));
    }

    // ------------------- Summary -----------------------

    @GetMapping("/summary/{meterId}")
    public ResponseEntity<Map<String, Object>> getSummary(@PathVariable String meterId) {
        return ResponseEntity.ok(service.getSummary(meterId));
    }

    // ------------------- Optional: Filter by Meter ID -----------------------

    @GetMapping("/meter/{meterId}")
    public ResponseEntity<List<EnergyReading>> getReadingsByMeter(@PathVariable String meterId) {
        List<EnergyReading> readings = service.getAllReadings()
                .stream()
                .filter(r -> r.getMeterId().equals(meterId))
                .toList();
        return ResponseEntity.ok(readings);
    }
}
