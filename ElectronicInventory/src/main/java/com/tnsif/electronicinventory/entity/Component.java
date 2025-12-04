package com.tnsif.electronicinventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Component name is required")
    private String name;

    @NotBlank(message = "Component type is required") 
    private String type;    // Resistor / Capacitor / IC / etc.

    private String description;  // Extra details about the component

    @Min(value = 0, message = "Quantity must be zero or positive")
    private int quantity;

    private String manufacturer; // Example: Texas Instruments, Samsung, etc.

    private double price; // per component price

    // Default Constructor
    public Component() {}

    // Parameterized Constructor
    public Component(String name, String type, String description, int quantity, String manufacturer, double price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    // -------------------- GETTERS & SETTERS --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
