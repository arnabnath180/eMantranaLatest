package com.example.emantrana.Admin.dto;

public class GetDoctorRequestDTO {
    private String email;

    public GetDoctorRequestDTO() {
    }

    public GetDoctorRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
