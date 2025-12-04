package com.tnsif.energy.controller;

import com.tnsif.energy.entity.BillingInfo;
import com.tnsif.energy.service.BillingInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin("*")
public class BillingInfoController {

    private final BillingInfoService service;

    public BillingInfoController(BillingInfoService service) {
        this.service = service;
    }

    // ------------------- CRUD -----------------------

    // Create / Save bill
    @PostMapping
    public ResponseEntity<BillingInfo> createBill(@RequestBody BillingInfo bill) {
        return ResponseEntity.ok(service.saveBill(bill));
    }

    // Get all bills
    @GetMapping
    public ResponseEntity<List<BillingInfo>> getAllBills() {
        return ResponseEntity.ok(service.getAllBills());
    }

    // Get bill for specific meter + month + year
    @GetMapping("/{meterId}/{year}/{month}")
    public ResponseEntity<BillingInfo> getBillForMonth(
            @PathVariable String meterId,
            @PathVariable int year,
            @PathVariable int month) {
        return ResponseEntity.ok(service.getBillForMonth(meterId, month, year));
    }

    // Delete bill
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteBill(id));
    }
}
