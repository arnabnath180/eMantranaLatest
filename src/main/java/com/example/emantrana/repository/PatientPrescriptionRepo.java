package com.example.emantrana.repository;

import com.example.emantrana.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientPrescriptionRepo extends JpaRepository<Prescription, Long> {
    Prescription findByPatientId(Long patientId);
}
// Patient : Prescription = 1 : N
