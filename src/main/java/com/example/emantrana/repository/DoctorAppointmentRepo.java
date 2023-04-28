package com.example.emantrana.repository;

import com.example.emantrana.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppointmentRepo extends JpaRepository<Appointment, Long> {
}
// Doctor : Appointment = 1 : N
