package com.tnsif.energy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "energy_readings")
public class EnergyReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double voltage;      // volts
    private double current;      // amperes
    private double power;        // watts
    private double energy;       // kWh
    private double frequency;    // Hz
    private double powerFactor;  // PF

    private LocalDateTime timestamp;  // reading recorded time

    private String meterId;      // reference to meter
    private String location;     // house, room, building

    public EnergyReading() {}

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public double getVoltage() { return voltage; }

    public void setVoltage(double voltage) { this.voltage = voltage; }

    public double getCurrent() { return current; }

    public void setCurrent(double current) { this.current = current; }

    public double getPower() { return power; }

    public void setPower(double power) { this.power = power; }

    public double getEnergy() { return energy; }

    public void setEnergy(double energy) { this.energy = energy; }

    public double getFrequency() { return frequency; }

    public void setFrequency(double frequency) { this.frequency = frequency; }

    public double getPowerFactor() { return powerFactor; }

    public void setPowerFactor(double powerFactor) { this.powerFactor = powerFactor; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMeterId() { return meterId; }

    public void setMeterId(String meterId) { this.meterId = meterId; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
