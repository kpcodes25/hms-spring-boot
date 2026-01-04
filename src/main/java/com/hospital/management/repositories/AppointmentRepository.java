package com.hospital.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
