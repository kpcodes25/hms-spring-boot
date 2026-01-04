package com.hospital.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	 // Search by name (case-insensitive)
    Page<Doctor> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Filter by specialization
    Page<Doctor> findBySpecializationIgnoreCase(String specialization, Pageable pageable);
}

