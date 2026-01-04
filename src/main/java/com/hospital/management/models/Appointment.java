package com.hospital.management.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // Appointment date
	    @Column(nullable = false)
	    private LocalDate appointmentDate;

	    // Appointment time	    
	    private LocalTime appointmentTime;

	    // Reason for visit
	    @Column(nullable = false)
	    private String reason;

	    // Appointment status (BOOKED, COMPLETED, CANCELLED)
	    @Column(nullable = false)
	    private String status;

	    // ---- Relationships ----

	    // Many appointments belong to one patient
	    @ManyToOne
	    @JoinColumn(name = "patient_id")
	    private Patient patient;

	    // Many appointments belong to one doctor
	    @ManyToOne
	    @JoinColumn(name = "doctor_id")
	    private Doctor doctor;
	}
