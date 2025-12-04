package com.tnsif.solar.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solar_readings")
public class SolarReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double voltage;        // Panel voltage
    private double current;        // Panel current
    private double power;          // Auto calculated (V × I)
    private double temperature;    // Module temperature
    private double irradiance;     // Sunlight intensity

    private String status;         // Low / Normal / Peak
    private LocalDateTime readingTime;

    public SolarReading() {
        this.readingTime = LocalDateTime.now();
    }

    // -------- Getters & Setters --------

    public Long getId() { return id; }

    public double getVoltage() { return voltage; }
    public void setVoltage(double voltage) { this.voltage = voltage; }

    public double getCurrent() { return current; }
    public void setCurrent(double current) { this.current = current; }

    public double getPower() { return power; }
    public void setPower(double power) { this.power = power; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getIrradiance() { return irradiance; }
    public void setIrradiance(double irradiance) { this.irradiance = irradiance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getReadingTime() { return readingTime; }
    public void setReadingTime(LocalDateTime readingTime) { this.readingTime = readingTime; }
}
