package com.tnsif.energy.service;

import com.tnsif.energy.entity.EnergyMeter;
import com.tnsif.energy.repository.EnergyMeterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyMeterService {

    private final EnergyMeterRepository repository;

    public EnergyMeterService(EnergyMeterRepository repository) {
        this.repository = repository;
    }

    public EnergyMeter saveMeter(EnergyMeter meter) {
        return repository.save(meter);
    }

    public List<EnergyMeter> getAllMeters() {
        return repository.findAll();
    }

    public EnergyMeter getMeterById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EnergyMeter updateMeter(Long id, EnergyMeter updated) {

        EnergyMeter existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setMeterId(updated.getMeterId());
        existing.setOwnerName(updated.getOwnerName());
        existing.setAddress(updated.getAddress());
        existing.setType(updated.getType());
        existing.setInstallationDate(updated.getInstallationDate());

        return repository.save(existing);
    }

    public String deleteMeter(Long id) {
        repository.deleteById(id);
        return "Meter deleted successfully";
    }

    public EnergyMeter getMeterByMeterId(String meterId) {
        return repository.findByMeterId(meterId);
    }
}
