package com.tnsif.homeautomation.service;

import com.tnsif.homeautomation.entity.Device;
import com.tnsif.homeautomation.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Get all devices
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // Get device by ID
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    // Add a new device
    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    // Update an existing device
    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    // Delete a device by ID
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    // Toggle the ON/OFF status of a device
    public Device toggleDeviceStatus(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        device.setStatus(!device.isStatus());
        return deviceRepository.save(device);
    }
}
