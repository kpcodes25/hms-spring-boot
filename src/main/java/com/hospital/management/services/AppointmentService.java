package com.hospital.management.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hospital.management.models.Appointment;
import com.hospital.management.repositories.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Page<Appointment> getAll(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return appointmentRepository.findAll(pageable);
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Page<Appointment> filterByStatus(String status, Pageable pageable) {
        return appointmentRepository.findByStatusIgnoreCase(status, pageable);
    }

    public Page<Appointment> filterByDoctor(Long doctorId, Pageable pageable) {
        return appointmentRepository.findByDoctorId(doctorId, pageable);
    }

    public Page<Appointment> filterByPatient(Long patientId, Pageable pageable) {
        return appointmentRepository.findByPatientId(patientId, pageable);
    }


    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
