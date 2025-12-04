package com.tnsif.solar.controller;

import com.tnsif.solar.entity.SolarReading;
import com.tnsif.solar.service.SolarReadingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/readings")
@CrossOrigin(origins = "*") // allows frontend access
public class SolarReadingController {

    private final SolarReadingService service;

    public SolarReadingController(SolarReadingService service) {
        this.service = service;
    }

    // -------------------------
    // CREATE
    // -------------------------
    @PostMapping("/add")
    public SolarReading addReading(@RequestBody SolarReading reading) {
        return service.addReading(reading);
    }

    // -------------------------
    // READ
    // -------------------------

    // Get all
    @GetMapping("/all")
    public List<SolarReading> getAll() {
        return service.getAllReadings();
    }

    // Get by ID
    @GetMapping("/{id}")
    public SolarReading getById(@PathVariable Long id) {
        return service.getReadingById(id);
    }

    // Get by status
    @GetMapping("/status/{status}")
    public List<SolarReading> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

    // Get latest reading
    @GetMapping("/latest")
    public SolarReading getLatest() {
        return service.getLatestReading();
    }

    // Get date range
    @GetMapping("/between")
    public List<SolarReading> getBetweenDates(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,

            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return service.getReadingsBetween(start, end);
    }

    // Get readings above certain power
    @GetMapping("/above-power/{power}")
    public List<SolarReading> getAbovePower(@PathVariable double power) {
        return service.getReadingsAbovePower(power);
    }

    // -------------------------
    // UPDATE
    // -------------------------
    @PutMapping("/update/{id}")
    public SolarReading updateReading(@PathVariable Long id, @RequestBody SolarReading reading) {
        return service.updateReading(id, reading);
    }

    // -------------------------
    // DELETE
    // -------------------------
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/delete-all")
    public String deleteAll() {
        return service.deleteAll();
    }
}
