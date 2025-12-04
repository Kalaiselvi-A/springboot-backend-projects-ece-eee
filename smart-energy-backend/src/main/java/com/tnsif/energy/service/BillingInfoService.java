package com.tnsif.energy.service;

import com.tnsif.energy.entity.BillingInfo;
import com.tnsif.energy.repository.BillingInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingInfoService {

    private final BillingInfoRepository repository;

    public BillingInfoService(BillingInfoRepository repository) {
        this.repository = repository;
    }

    public BillingInfo saveBill(BillingInfo bill) {

        double RATE_PER_KWH = 7.5;

        double energyCharge = bill.getTotalEnergy() * RATE_PER_KWH;
        double total = energyCharge + bill.getFixedCharge() + bill.getTax();

        bill.setAmount(total);

        return repository.save(bill);
    }

    public List<BillingInfo> getAllBills() {
        return repository.findAll();
    }

    public BillingInfo getBillForMonth(String meterId, int month, int year) {
        return repository.findByMeterIdAndMonthAndYear(meterId, month, year);
    }

    public String deleteBill(Long id) {
        repository.deleteById(id);
        return "Bill deleted successfully";
    }
}
