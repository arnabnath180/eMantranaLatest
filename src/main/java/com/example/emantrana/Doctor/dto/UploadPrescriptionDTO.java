package com.example.emantrana.Doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UploadPrescriptionDTO {
    Long patientId;
    Long doctorId;
    String description;
    String medicine;

    public UploadPrescriptionDTO(
            @JsonProperty("patientId")Long patientId,
            @JsonProperty("doctorId")Long doctorId,
            @JsonProperty("description")String description,
            @JsonProperty("medicine")String medicine
    ) {

        this.patientId = patientId;
        this.doctorId =  doctorId;
        this.description = description;
        this.medicine = medicine;

    }
}
