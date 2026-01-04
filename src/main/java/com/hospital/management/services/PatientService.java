package com.hospital.management.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hospital.management.models.Patient;
import com.hospital.management.repositories.PatientRepository;

@Service
public class PatientService {

	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

	private final PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Patient savePatient(Patient patient) {

		logger.info("Patient  added Successfully");
		return patientRepository.save(patient);
	}

	public Page<Patient> getAllPatients(int page, int size, String sortBy, String direction) {

		logger.info("Fetching patients - page {}, size {}", page, size);

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);
		return patientRepository.findAll(pageable);
	}

	public Page<Patient> searchByName(String name, Pageable pageable) {
		logger.info("Searching patient by name: {}", name);
		return patientRepository.findByNameContainingIgnoreCase(name, pageable);
	}

	public Page<Patient> filterByGender(String gender, Pageable pageable) {
		logger.info("Filtering patient by gender: {}", gender);
		return patientRepository.findByGenderIgnoreCase(gender, pageable);
	}
	
	 public Page<Patient> filterByDisease(String disease, Pageable pageable) {
	        logger.info("Filtering patient by disease: {}", disease);
	        return patientRepository.findByDiseaseContainingIgnoreCase(disease, pageable);
	    }
	 
	 public Page<Patient> filterByAgeRange(int minAge, int maxAge, Pageable pageable) {
	        logger.info("Filtering patients by age range {} - {}", minAge, maxAge);
	        return patientRepository.findByAgeBetween(minAge, maxAge, pageable);
	    }

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
	}

	public Patient updatePatient(Long id, Patient updatedPatient) {
		Patient patient = getPatientById(id);

		patient.setName(updatedPatient.getName());
		patient.setGender(updatedPatient.getGender());
		patient.setAge(updatedPatient.getAge());

		return patientRepository.save(patient);
	}

	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}
}
