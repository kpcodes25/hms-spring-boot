package com.hospital.management.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital.management.models.Bill;
import com.hospital.management.repositories.BillRepository;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public Page<Bill> getBills(String patientName, String status, Pageable pageable) {

        if (patientName != null && status != null) {
            return billRepository
                    .findByPatientNameContainingIgnoreCaseAndStatus(patientName, status, pageable);
        }

        if (patientName != null) {
            return billRepository
                    .findByPatientNameContainingIgnoreCase(patientName, pageable);
        }

        if (status != null) {
            return billRepository.findByStatus(status, pageable);
        }

        return billRepository.findAll(pageable);
    }

    public Bill getById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    public void delete(Long id) {
        billRepository.deleteById(id);
    }
}
