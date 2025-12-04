package com.tnsif.iot.dashboard.controller;

import com.tnsif.iot.dashboard.entity.SensorReading;
import com.tnsif.iot.dashboard.service.SensorReadingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
@CrossOrigin("*")  // Allow frontend to access API
public class SensorReadingController {

    private final SensorReadingService service;

    public SensorReadingController(SensorReadingService service) {
        this.service = service;
    }

    // CREATE - Add sensor reading
    @PostMapping("/add")
    public SensorReading addReading(@RequestBody SensorReading reading) {
        return service.saveReading(reading);
    }

    // READ - Get all sensor readings
    @GetMapping("/all")
    public List<SensorReading> getAllReadings() {
        return service.getAllReadings();
    }

    // READ - Get one reading by ID
    @GetMapping("/{id}")
    public SensorReading getById(@PathVariable Long id) {
        return service.getReadingById(id);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteReading(id);
        return "Sensor reading deleted with ID: " + id;
    }
}
