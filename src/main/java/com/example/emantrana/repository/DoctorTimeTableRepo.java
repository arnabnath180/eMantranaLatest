package com.example.emantrana.repository;

import com.example.emantrana.models.DoctorTimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DoctorTimeTableRepo extends JpaRepository <DoctorTimeTable, Long> {
    @Transactional
    void deleteByDoctorId(Long id);
}
// Doctor : DoctorTimeTable = 1 : N
