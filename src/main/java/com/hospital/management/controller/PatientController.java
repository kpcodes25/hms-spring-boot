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

import com.hospital.management.models.Patient;
import com.hospital.management.services.PatientService;

@RestController
@RequestMapping("/api/v1.0/patients")
public class PatientController {
	
	private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Create Patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    //  Get All Patients
    @GetMapping
    public Page<Patient> getAllPatients(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction)  {

        return patientService.getAllPatients(page, size, sortBy, direction);
    }
    
    // Search by name
    @GetMapping("/search")
    public Page<Patient> searchPatients(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return patientService.searchByName(name, pageable);
    }

    // Filter by gender
    @GetMapping("/filter/gender")
    public Page<Patient> filterByGender(
            @RequestParam String gender,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return patientService.filterByGender(gender, pageable);
    }

    // Filter by disease
    @GetMapping("/filter/disease")
    public Page<Patient> filterByDisease(
            @RequestParam(name = "disease") String disease,
            @RequestParam(name = "page",  defaultValue = "0") int page,
            @RequestParam(name = "size" ,defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return patientService.filterByDisease(disease, pageable);
    }

    // Filter by age range
    @GetMapping("/filter/age")
    public Page<Patient> filterByAge(
            @RequestParam(name = "min") int min,
            @RequestParam(name = "max") int max,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return patientService.filterByAgeRange(min, max, pageable);
    }

    // Get Patient by ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    // Update Patient
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable("id") Long id,
            @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    //  Delete Patient
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return "Patient deleted successfully";
    }
}
