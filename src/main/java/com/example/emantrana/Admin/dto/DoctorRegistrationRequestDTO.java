package com.example.emantrana.Admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DoctorRegistrationRequestDTO {
    private String fname;
    private String lname;
    private String type;
    private String email;

    private String ph_number;



    List<TimeTableDTO> timeTable=new ArrayList<>();

    public DoctorRegistrationRequestDTO() {
    }

    public DoctorRegistrationRequestDTO(
            @JsonProperty("fname") String fname,
            @JsonProperty("lname") String lname,
            @JsonProperty("type") String type,
            @JsonProperty("email") String email,
            @JsonProperty("ph_number") String ph_number,
            @JsonProperty("time_table") List<TimeTableDTO> timeTable) {


        this.fname = fname;
        this.lname = lname;
        this.type = type;
        this.email = email;
        this.ph_number = ph_number;


        this.timeTable = timeTable;
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

    public List<TimeTableDTO> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTableDTO> timeTable) {
        this.timeTable = timeTable;
    }
}
