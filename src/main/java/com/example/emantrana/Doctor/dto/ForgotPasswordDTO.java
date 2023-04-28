package com.example.emantrana.Doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForgotPasswordDTO {
    private String email;

    public  ForgotPasswordDTO(
            @JsonProperty("email")String email
    ) {
        this.email = email; //Base64.getEncoder().encodeToString(phoneNumber.getBytes());
    }
}
