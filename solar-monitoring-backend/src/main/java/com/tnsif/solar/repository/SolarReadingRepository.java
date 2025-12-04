package com.tnsif.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tnsif.solar.entity.SolarReading;

import java.time.LocalDateTime;
import java.util.List;

public interface SolarReadingRepository extends JpaRepository<SolarReading, Long> {

    List<SolarReading> findByStatus(String status);

    SolarReading findTopByOrderByReadingTimeDesc();

    List<SolarReading> findByReadingTimeBetween(LocalDateTime start, LocalDateTime end);

    List<SolarReading> findByPowerGreaterThan(double power);
}
