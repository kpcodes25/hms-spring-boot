package com.hospital.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.management.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	// Search by name (case-insensitive)
    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Filter by gender
    Page<Patient> findByGenderIgnoreCase(String gender, Pageable pageable);

    // Filter by disease
    Page<Patient> findByDiseaseContainingIgnoreCase(String disease, Pageable pageable);

    // Filter by age range
    Page<Patient> findByAgeBetween(int minAge, int maxAge, Pageable pageable);

}
