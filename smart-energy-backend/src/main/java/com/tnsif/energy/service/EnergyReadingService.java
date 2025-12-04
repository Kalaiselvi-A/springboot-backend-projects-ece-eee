package com.tnsif.energy.service;

import com.tnsif.energy.entity.EnergyReading;
import com.tnsif.energy.repository.EnergyReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnergyReadingService {

    private final EnergyReadingRepository repository;

    public EnergyReadingService(EnergyReadingRepository repository) {
        this.repository = repository;
    }

    // ---------------------- CRUD -----------------------------

    public EnergyReading saveReading(EnergyReading reading) {

        // Auto-calc power (W = V * I * PF)
        double power = reading.getVoltage() * reading.getCurrent() * reading.getPowerFactor();
        reading.setPower(power);

        // Auto timestamp if null
        if (reading.getTimestamp() == null) {
            reading.setTimestamp(LocalDateTime.now());
        }

        return repository.save(reading);
    }

    public List<EnergyReading> getAllReadings() {
        return repository.findAll();
    }

    public EnergyReading getReadingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EnergyReading updateReading(Long id, EnergyReading updated) {

        EnergyReading existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setVoltage(updated.getVoltage());
        existing.setCurrent(updated.getCurrent());
        existing.setPowerFactor(updated.getPowerFactor());
        existing.setFrequency(updated.getFrequency());
        existing.setEnergy(updated.getEnergy());
        existing.setLocation(updated.getLocation());
        existing.setMeterId(updated.getMeterId());
        existing.setTimestamp(updated.getTimestamp());

        // recalc power
        double power = updated.getVoltage() * updated.getCurrent() * updated.getPowerFactor();
        existing.setPower(power);

        return repository.save(existing);
    }

    public String deleteReading(Long id) {
        repository.deleteById(id);
        return "Reading deleted successfully";
    }

    // ---------------------- DAILY USAGE -----------------------------

    public double getDailyUsage(String meterId, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);

        List<EnergyReading> list = repository.findByTimestampBetween(start, end);

        return list.stream()
                .filter(r -> r.getMeterId().equals(meterId))
                .mapToDouble(EnergyReading::getEnergy)
                .sum();
    }

    // ---------------------- MONTHLY USAGE -----------------------------

    public double getMonthlyUsage(String meterId, int year, int month) {

        YearMonth ym = YearMonth.of(year, month);

        LocalDateTime start = ym.atDay(1).atStartOfDay();
        LocalDateTime end = ym.atEndOfMonth().atTime(23, 59, 59);

        List<EnergyReading> list = repository.findByTimestampBetween(start, end);

        return list.stream()
                .filter(r -> r.getMeterId().equals(meterId))
                .mapToDouble(EnergyReading::getEnergy)
                .sum();
    }

    // --------------------- SUMMARY API -----------------------------

    public Map<String, Object> getSummary(String meterId) {

        Map<String, Object> map = new HashMap<>();

        LocalDate today = LocalDate.now();

        map.put("meterId", meterId);

        map.put("todayEnergy", getDailyUsage(meterId, today));
        map.put("yesterdayEnergy", getDailyUsage(meterId, today.minusDays(1)));

        double weekEnergy = 0;
        for (int i = 0; i < 7; i++) {
            weekEnergy += getDailyUsage(meterId, today.minusDays(i));
        }
        map.put("weeklyEnergy", weekEnergy);

        map.put("monthlyEnergy", getMonthlyUsage(meterId, today.getYear(), today.getMonthValue()));

        return map;
    }
}
