package com.hospital.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	 // Filter by status
    Page<Appointment> findByStatusIgnoreCase(String status, Pageable pageable);

    // Filter by doctor
    Page<Appointment> findByDoctorId(Long doctorId, Pageable pageable);

    // Filter by patient
    Page<Appointment> findByPatientId(Long patientId, Pageable pageable);
}
