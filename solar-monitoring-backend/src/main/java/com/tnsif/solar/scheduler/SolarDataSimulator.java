package com.tnsif.solar.scheduler;

import com.tnsif.solar.entity.SolarReading;
import com.tnsif.solar.service.SolarReadingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SolarDataSimulator {

    private final SolarReadingService service;
    private final Random random = new Random();

    public SolarDataSimulator(SolarReadingService service) {
        this.service = service;
    }

    // Runs every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void generateData() {

        SolarReading reading = new SolarReading();

        // Simulated values
        double voltage = 10 + random.nextDouble() * 30;   // 10V – 40V
        double current = 1 + random.nextDouble() * 9;     // 1A – 10A
        double temperature = 25 + random.nextDouble() * 20; // 25°C – 45°C
        double irradiance = 200 + random.nextDouble() * 800; // 200 – 1000 W/m2

        reading.setVoltage(voltage);
        reading.setCurrent(current);
        reading.setTemperature(temperature);
        reading.setIrradiance(irradiance);

        service.addReading(reading);

        System.out.println("AUTO DATA GENERATED: " + voltage + "V, " + current + "A");
    }
}
