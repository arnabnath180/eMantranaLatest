package com.example.emantrana.Admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginAdminDTO {
    private String email; // userId of patient
    private String password;
    public  LoginAdminDTO(
            @JsonProperty("email")String email,
            @JsonProperty("password")String password)
    {

        this.email = email; //Base64.getEncoder().encodeToString(phoneNumber.getBytes());
        this.password =  password;//Base64.getEncoder().encodeToString(password.getBytes());

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
