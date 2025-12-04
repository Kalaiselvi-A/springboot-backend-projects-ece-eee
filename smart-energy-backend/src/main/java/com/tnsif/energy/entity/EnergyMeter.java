package com.tnsif.energy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "energy_meter")
public class EnergyMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String meterId;        // ex: MTR001
    private String ownerName;      // customer name
    private String address;        // full address
    private String type;           // single-phase / three-phase
    private String installationDate;

    public EnergyMeter() {}

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getMeterId() { return meterId; }

    public void setMeterId(String meterId) { this.meterId = meterId; }

    public String getOwnerName() { return ownerName; }

    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getInstallationDate() { return installationDate; }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }
}
