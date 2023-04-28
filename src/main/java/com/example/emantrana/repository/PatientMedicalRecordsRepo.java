package com.example.emantrana.repository;

import com.example.emantrana.models.MedicalRecords;
import com.example.emantrana.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientMedicalRecordsRepo extends JpaRepository<MedicalRecords, Long> {

//    @PersistenceContext
//    private EntityManager entityManager;
 List<MedicalRecords> findByPatientId(Long patientId);
    Optional<MedicalRecords> findByPatientIdAndAndFileName(Long patientId,String fileName);

    Optional<MedicalRecords> findByFileName(String fileName);
    //@Query("delete from medical_records M where M.patient=?2 and M.fileName=?1")

}
// Patient : MedicalRecords = 1 : N
