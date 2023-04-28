package com.example.emantrana.Doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForgetPasswordRequestDTO {
    String token;
    String password;

    public  ForgetPasswordRequestDTO(
            @JsonProperty("token")String token,
            @JsonProperty("password")String password
    ) {

        this.password = password; //Base64.getEncoder().encodeToString(phoneNumber.getBytes());
        this.password =  password;//Base64.getEncoder().encodeToString(password.getBytes());

    }
}
