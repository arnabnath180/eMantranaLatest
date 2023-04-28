package com.example.emantrana.repository;

import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);

    Doctor findByResetToken(String token);

    Optional<Doctor> findById(Long id);


    @Query(value = "SELECT * FROM doctor where type=?1", nativeQuery = true)
    List<Doctor> getAll(String type);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "Update doctor set fname=?1, lname=?2, email=?3, phone=?4, type=?5 where id=?6", nativeQuery = true)
    void updateDoctor(String fname,String lname,String email,String ph_number,String type,Long id);




    // Doctor findByIdentifier(String identifier);
}
