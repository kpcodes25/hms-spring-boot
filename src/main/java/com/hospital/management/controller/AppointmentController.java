package com.hospital.management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.models.Appointment;
import com.hospital.management.services.AppointmentService;

@RestController
@RequestMapping("/api/v1.0/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping
    public Page<Appointment> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        return appointmentService.getAll(page, size, sortBy, direction);
    }

    // Filter by status
    @GetMapping("/filter/status")
    public Page<Appointment> filterByStatus(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return appointmentService.filterByStatus(status, pageable);
    }

    // Filter by doctor
    @GetMapping("/filter/doctor")
    public Page<Appointment> filterByDoctor(
            @RequestParam(name = "doctorId") Long doctorId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return appointmentService.filterByDoctor(doctorId, pageable);
    }

    // Filter by patient
    @GetMapping("/filter/patient")
    public Page<Appointment> filterByPatient(
            @RequestParam(name = "patientId") Long patientId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return appointmentService.filterByPatient(patientId, pageable);
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable("id") Long id) {
        return appointmentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
    }
}