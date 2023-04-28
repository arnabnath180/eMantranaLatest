package com.example.emantrana.patient.dtos;

import lombok.Data;

@Data
public class patientRegisterResponseDTO {
    private Long id;
    private String phoneNumber; // userId of patient

    private String password;

    private String Fname;

    private String Lname;
    private String email;

    private String dateOfBirth; // should it be string or date data type??

    private String Gender;

}
