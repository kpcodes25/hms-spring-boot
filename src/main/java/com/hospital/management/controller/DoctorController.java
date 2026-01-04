package com.hospital.management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.models.Doctor;
import com.hospital.management.services.DoctorService;

@RestController
@RequestMapping("/api/v1.0/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @GetMapping
    public Page<Doctor> getAllDoctors(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        return doctorService.getAllDoctors(page, size, sortBy, direction);
    }

    // ✅ Search by name
    @GetMapping("/search")
    public Page<Doctor> searchDoctors(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return doctorService.searchByName(name, pageable);
    }

    // ✅ Filter by specialization
    @GetMapping("/filter/specialization")
    public Page<Doctor> filterBySpecialization(
            @RequestParam(name = "specialization") String specialization,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return doctorService.filterBySpecialization(specialization, pageable);
    }

    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable("id") Long id,
            @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
    }
}
