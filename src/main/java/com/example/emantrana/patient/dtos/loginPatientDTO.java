package com.example.emantrana.patient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Base64;

@Data
public class loginPatientDTO {
    private String phoneNumber; // userId of patient
    private String password;
    public  loginPatientDTO(
            @JsonProperty("phoneNumber")String phoneNumber,
            @JsonProperty("password")String password
    ) {

        this.phoneNumber = phoneNumber; //Base64.getEncoder().encodeToString(phoneNumber.getBytes());
        this.password =  password;//Base64.getEncoder().encodeToString(password.getBytes());

    }


}
