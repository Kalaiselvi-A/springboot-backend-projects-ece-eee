package com.tnsif.homeautomation.controller;

import com.tnsif.homeautomation.entity.Device;
import com.tnsif.homeautomation.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Get all devices
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // Get a device by ID
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable Long id) {
        return deviceService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a new device
    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceService.addDevice(device);
    }

    // Update an existing device
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        return deviceService.getDeviceById(id)
                .map(existing -> {
                    device.setId(existing.getId());
                    return ResponseEntity.ok(deviceService.updateDevice(device));
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a device
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    // Toggle device ON/OFF status
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Device> toggleDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.toggleDeviceStatus(id));
    }
}
