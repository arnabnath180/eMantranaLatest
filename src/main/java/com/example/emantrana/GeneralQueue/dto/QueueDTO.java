package com.example.emantrana.GeneralQueue.dto;

import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.Patient;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class QueueDTO {
    private Long patient_id;

    private Long doctor_id;
    private String status;

    public QueueDTO(
            @JsonProperty("patient_id") Long patient_id,
            @JsonProperty("doctor_id") Long doctor_id,
            @JsonProperty("status") String status) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.status = status;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
