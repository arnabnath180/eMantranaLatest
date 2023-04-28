package com.example.emantrana.Doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DoctorloginResponseDTO {
    String Doctortoken;
    Long DoctorId;
    boolean Status=true;
    String DoctorEmail;

}
