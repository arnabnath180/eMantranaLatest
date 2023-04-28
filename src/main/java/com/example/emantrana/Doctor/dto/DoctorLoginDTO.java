package com.example.emantrana.Doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DoctorLoginDTO {
    private String email; // userId of patient
    private String password;
    public  DoctorLoginDTO(
            @JsonProperty("email")String email,
            @JsonProperty("password")String password
    ) {

        this.email = email;
        this.password =  password;

    }
}
