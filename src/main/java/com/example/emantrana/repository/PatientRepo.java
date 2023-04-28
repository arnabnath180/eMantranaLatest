package com.example.emantrana.repository;

import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    Patient findByPhoneNumber(String phoneNumber);




}
// Patient : General_queue = 1 : 1
