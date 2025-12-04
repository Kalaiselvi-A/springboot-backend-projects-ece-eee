package com.tnsif.homeautomation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // Unique ID

    @Column(nullable = false)
    private String name;        // Device name (e.g., Light, Fan, AC)

    @Column(nullable = false)
    private String type;        // Device type (Electrical, AC, etc.)

    @Column(nullable = false)
    private boolean status;     // ON/OFF status

    @Column(nullable = true)
    private String location;    // Room or location

    @Column(nullable = true)
    private String model;       // Device model

    @Column(nullable = true)
    private String ipAddress;   // Simulated IP address

    public Device() {
    }

    public Device(String name, String type, boolean status, String location, String model, String ipAddress) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
        this.model = model;
        this.ipAddress = ipAddress;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
