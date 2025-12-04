package com.tnsif.energy.repository;

import com.tnsif.energy.entity.EnergyReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EnergyReadingRepository extends JpaRepository<EnergyReading, Long> {

    // Find by Meter ID
    List<EnergyReading> findByMeterId(String meterId);

    // Find readings between two timestamps (daily/monthly)
    @Query("SELECT e FROM EnergyReading e WHERE e.timestamp BETWEEN :start AND :end")
    List<EnergyReading> findReadingsBetween(LocalDateTime start, LocalDateTime end);

	List<EnergyReading> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

}
