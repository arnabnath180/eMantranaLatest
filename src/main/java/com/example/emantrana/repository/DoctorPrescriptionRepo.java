package com.example.emantrana.repository;

import com.example.emantrana.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorPrescriptionRepo extends JpaRepository<Prescription, Long> {
}
// Doctor : Prescription = 1 : N
