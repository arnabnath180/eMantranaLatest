package com.example.emantrana.patient.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;
import java.util.List;

public class GetDoctorResponseDTO {
    private Long id;
    private String fname;
    private String lname;
    private String type;
    private String email;

    private String ph_number;


    public GetDoctorResponseDTO() {
    }

    public GetDoctorResponseDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("fname") String fname,
            @JsonProperty("lname") String lname,
            @JsonProperty("type") String type,
            @JsonProperty("email") String email,
            @JsonProperty("ph_number") String ph_number) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.type = type;
        this.email = email;
        this.ph_number = ph_number;

    }


    public GetDoctorResponseDTO(String fname, String lname, String type, String email, String ph_number) {
        this.fname = fname;
        this.lname = lname;
        this.type = type;
        this.email = email;
        this.ph_number = ph_number;

    }




    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
