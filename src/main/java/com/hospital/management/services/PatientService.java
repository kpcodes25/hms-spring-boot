package com.hospital.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.management.models.Patient;
import com.hospital.management.repositories.PatientRepository;

@Service
public class PatientService {

	private final PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
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
