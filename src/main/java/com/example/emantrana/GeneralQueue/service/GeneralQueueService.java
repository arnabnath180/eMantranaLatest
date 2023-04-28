package com.example.emantrana.GeneralQueue.service;

import com.example.emantrana.GeneralQueue.dto.QueueDTO;
import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.General_Queue;
import com.example.emantrana.models.Patient;
import com.example.emantrana.repository.DoctorGeneral_QueueRepo;
import com.example.emantrana.repository.DoctorRepo;
import com.example.emantrana.repository.General_QueueRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralQueueService {
    @Autowired
    private DoctorGeneral_QueueRepo gqr;
    @Autowired
    private ModelMapper modelMapper;
    public boolean add(QueueDTO q){
        Doctor d = new Doctor(q.getDoctor_id());
        Patient p = new Patient(q.getPatient_id());
        General_Queue gq1 = new General_Queue(p, d, q.getStatus());
        gqr.save(gq1);
        return true;
    }

    public void delete(Long id){
        gqr.deleteByPatientId(id);
    }

    public List<QueueDTO> get(Long id){
        List<QueueDTO> qdto=new ArrayList<>();
        List<General_Queue> gq = gqr.get(id,"accepted");
        for(General_Queue i : gq){
            qdto.add(new QueueDTO(i.getPatient().getId(),i.getDoctor().getId(),i.getStatus()));
        }
        return qdto;
    }

    public void deleteDoctor(Long id){
        gqr.deleteByDoctorId(id);
    }


}
