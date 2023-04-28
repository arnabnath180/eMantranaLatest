package com.example.emantrana.patient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class registerPatientDTO {
    private String phoneNumber; // userId of patient
    private String password;
    private String Fname;
    private String Lname;
    private String email;
    private String dateOfBirth; // should it be string or date data type??
    private String Gender;

    public registerPatientDTO(
            @JsonProperty("phoneNumber")String phoneNumber,
            @JsonProperty("password")String password,
            @JsonProperty("Fname")String Fname,
            @JsonProperty("Lname")String Lname,
            @JsonProperty("email")String email,
            @JsonProperty("dateOfBirth")String dateOfBirth,
            @JsonProperty("Gender")String Gender) {

        this.phoneNumber = phoneNumber;
        this.password = password;
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.Gender = Gender;
    }


}
