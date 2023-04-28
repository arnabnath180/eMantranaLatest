package com.example.emantrana.repository;

import com.example.emantrana.models.Doctor_Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Doctor_DoctorPatientRepo extends JpaRepository<Doctor_Patient, Long> {
}
// Doctor : Doctor_Patient = 1 : N
