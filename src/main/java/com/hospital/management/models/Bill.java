package com.hospital.management.models;

import java.time.LocalDate;

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
@Table(name = "bills")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bill number (can be shown to patient)
    @Column(unique = true, nullable = false)
    private String billNumber;

    // Total bill amount
    @Column(nullable = false)
    private double amount;

    // Payment status (PAID / UNPAID)
    @Column(nullable = false)
    private String status;

    // Date of billing
    private LocalDate billDate;

    // ---- Relationships ----

    // Each bill belongs to one patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Each bill may be issued by one doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
