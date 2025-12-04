package com.tnsif.solar.service;

import com.tnsif.solar.entity.SolarReading;
import com.tnsif.solar.repository.SolarReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolarReadingService {

    private final SolarReadingRepository repository;

    public SolarReadingService(SolarReadingRepository repository) {
        this.repository = repository;
    }

    // ---------------------------
    // CREATE
    // ---------------------------
    public SolarReading addReading(SolarReading reading) {

        // Auto-calculate power
        double calculatedPower = reading.getVoltage() * reading.getCurrent();
        reading.setPower(calculatedPower);

        // Auto-status
        if (calculatedPower < 100)
            reading.setStatus("Low");
        else if (calculatedPower < 500)
            reading.setStatus("Normal");
        else
            reading.setStatus("Peak");

        reading.setReadingTime(LocalDateTime.now());
        return repository.save(reading);
    }

    // ---------------------------
    // READ
    // ---------------------------
    public List<SolarReading> getAllReadings() {
        return repository.findAll();
    }

    public SolarReading getReadingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Find by Status
    public List<SolarReading> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    // Latest reading
    public SolarReading getLatestReading() {
        return repository.findTopByOrderByReadingTimeDesc();
    }

    // Date range filtering
    public List<SolarReading> getReadingsBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByReadingTimeBetween(start, end);
    }

    // Power greater than a value
    public List<SolarReading> getReadingsAbovePower(double power) {
        return repository.findByPowerGreaterThan(power);
    }

    // ---------------------------
    // UPDATE
    // ---------------------------
    public SolarReading updateReading(Long id, SolarReading newData) {

        SolarReading old = repository.findById(id).orElse(null);
        if (old == null) return null;

        old.setVoltage(newData.getVoltage());
        old.setCurrent(newData.getCurrent());
        old.setTemperature(newData.getTemperature());
        old.setIrradiance(newData.getIrradiance());

        // recalculate power
        double calculatedPower = newData.getVoltage() * newData.getCurrent();
        old.setPower(calculatedPower);

        if (calculatedPower < 100)
            old.setStatus("Low");
        else if (calculatedPower < 500)
            old.setStatus("Normal");
        else
            old.setStatus("Peak");

        return repository.save(old);
    }

    // ---------------------------
    // DELETE
    // ---------------------------
    public String deleteById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Reading deleted successfully!";
        }
        return "No record found!";
    }

    public String deleteAll() {
        repository.deleteAll();
        return "All readings deleted successfully!";
    }
}
