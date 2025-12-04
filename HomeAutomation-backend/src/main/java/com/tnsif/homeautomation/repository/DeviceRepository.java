package com.tnsif.homeautomation.repository;

import com.tnsif.homeautomation.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    // JpaRepository provides basic CRUD methods (save, findAll, findById, deleteById)
    // You can add custom query methods here if needed
}
