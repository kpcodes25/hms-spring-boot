package com.hospital.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.models.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
	// Search by patient name (example)
    Page<Bill> findByPatientNameContainingIgnoreCase(String patientName, Pageable pageable);

    // Filter by status (PAID / UNPAID)
    Page<Bill> findByStatus(String status, Pageable pageable);

    // Search + filter together
    Page<Bill> findByPatientNameContainingIgnoreCaseAndStatus(
            String patientName, String status, Pageable pageable);
}

