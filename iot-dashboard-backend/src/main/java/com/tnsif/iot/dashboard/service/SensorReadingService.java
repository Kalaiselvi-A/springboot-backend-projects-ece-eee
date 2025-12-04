package com.tnsif.iot.dashboard.service;

import com.tnsif.iot.dashboard.entity.SensorReading;
import com.tnsif.iot.dashboard.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingService {

    private final SensorReadingRepository repo;

    public SensorReadingService(SensorReadingRepository repo) {
        this.repo = repo;
    }

    // Save one sensor reading
    public SensorReading saveReading(SensorReading reading) {
        return repo.save(reading);
    }

    // Fetch all readings
    public List<SensorReading> getAllReadings() {
        return repo.findAll();
    }

    // Fetch reading by ID
    public SensorReading getReadingById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Delete reading by ID
    public void deleteReading(Long id) {
        repo.deleteById(id);
    }
}
