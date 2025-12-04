package com.tnsif.energy.repository;

import com.tnsif.energy.entity.EnergyMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyMeterRepository extends JpaRepository<EnergyMeter, Long> {

    // Find meter by meterId code
    EnergyMeter findByMeterId(String meterId);

}
