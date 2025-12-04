package com.tnsif.energy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "billing_info")
public class BillingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String meterId;
    private int month;
    private int year;
    private double totalEnergy;     // kWh
    private double amount;          // INR
    private double fixedCharge;     // constant
    private double tax;

    public BillingInfo() {}

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getMeterId() { return meterId; }

    public void setMeterId(String meterId) { this.meterId = meterId; }

    public int getMonth() { return month; }

    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public double getTotalEnergy() { return totalEnergy; }

    public void setTotalEnergy(double totalEnergy) { this.totalEnergy = totalEnergy; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public double getFixedCharge() { return fixedCharge; }

    public void setFixedCharge(double fixedCharge) { this.fixedCharge = fixedCharge; }

    public double getTax() { return tax; }

    public void setTax(double tax) { this.tax = tax; }
}
