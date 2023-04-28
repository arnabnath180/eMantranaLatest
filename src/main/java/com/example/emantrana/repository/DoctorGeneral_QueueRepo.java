package com.example.emantrana.repository;

import com.example.emantrana.GeneralQueue.dto.DoctorRejectedDTO;
import com.example.emantrana.GeneralQueue.dto.QueueDTO;
import com.example.emantrana.models.General_Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;

@Repository
public interface DoctorGeneral_QueueRepo extends JpaRepository<General_Queue, Long> {
    public General_Queue findByPatientId(Long id);

    //@Query(value = "Delete * FROM general_queue q where q.patient_id=$1", nativeQuery = true)

    @Transactional
    public void deleteByPatientId(Long id);

    @Query(value = "Select * from general_queue where doctor_id=?1 and status=?2", nativeQuery = true)
    public List<General_Queue> get(Long id, String s);

    @Transactional
    public void deleteByDoctorId(Long id);

    @Query(value = "Select doctor_id from general_queue where patient_id=?1 and status=?2", nativeQuery = true)
    public List<Long> getDoctorRejected(Long id, String s);




}
// Doctor : General_queue = 1 : N
