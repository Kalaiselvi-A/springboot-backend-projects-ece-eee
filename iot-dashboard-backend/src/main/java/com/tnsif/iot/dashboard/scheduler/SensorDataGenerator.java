package com.tnsif.iot.dashboard.scheduler;

import com.tnsif.iot.dashboard.entity.SensorReading;
import com.tnsif.iot.dashboard.service.SensorReadingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SensorDataGenerator {

    private final SensorReadingService service;
    private final Random random = new Random();

    public SensorDataGenerator(SensorReadingService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 5000)  // Every 5 seconds
    public void generateDummyData() {

        SensorReading reading = new SensorReading();
        reading.setTemperature(20 + random.nextDouble() * 10); // 20–30°C
        reading.setHumidity(40 + random.nextDouble() * 20);   // 40–60%
        reading.setVoltage(3.0 + random.nextDouble());        // 3.0–4.0v
        reading.setDeviceId("ESP32-01");
        reading.setLocation("Lab-1");

        // Extra fields
        reading.setPressure(1000 + random.nextDouble() * 50);   // 1000–1050
        reading.setAirQuality(50 + random.nextDouble() * 40);   // 50–90 AQI

        service.saveReading(reading);

        System.out.println("Dummy sensor reading saved!");
    }
}
