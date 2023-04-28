package com.example.emantrana.patient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class updatePasswordDTO {
    String phoneNumber;
    String password;

    public  updatePasswordDTO(
            @JsonProperty("phoneNumber")String phoneNumber,
            @JsonProperty("password")String password
    ) {

        this.phoneNumber = phoneNumber; //Base64.getEncoder().encodeToString(phoneNumber.getBytes());
        this.password =  password;//Base64.getEncoder().encodeToString(password.getBytes());

    }
}
