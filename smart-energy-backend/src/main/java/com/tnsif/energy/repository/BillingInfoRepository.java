package com.tnsif.energy.repository;

import com.tnsif.energy.entity.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {

    // Get monthly bill for meter
    BillingInfo findByMeterIdAndMonthAndYear(String meterId, int month, int year);

    // Get bills for specific meter
    List<BillingInfo> findByMeterId(String meterId);
}
