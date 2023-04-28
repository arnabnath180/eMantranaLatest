package com.example.emantrana.patient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdateProfileDTO {
    private String Fname;
    private String Lname;
    private String dateOfBirth;
    private String Gender;

    public UpdateProfileDTO(
            @JsonProperty("Fname")String Fname,
            @JsonProperty("Lname")String Lname,
            @JsonProperty("dateOfBirth") String dateOfBirth,
            @JsonProperty("Gender") String Gender
    ) {

       this.Fname = Fname;
       this.Lname=Lname;
       this.dateOfBirth = dateOfBirth;
       this.Gender = Gender;

    }
}
//    private String phoneNumber; // userId of patient
//    private String password;
//    public  loginPatientDTO(
//            @JsonProperty("phoneNumber")String phoneNumber,
//            @JsonProperty("password")String password
//    ) {
//
//        this.phoneNumber = phoneNumber; //Base64.getEncoder().encodeToString(phoneNumber.getBytes());
//        this.password =  password;//Base64.getEncoder().encodeToString(password.getBytes());
//
//    }