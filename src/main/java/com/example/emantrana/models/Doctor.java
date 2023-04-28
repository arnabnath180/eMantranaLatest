package com.example.emantrana.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
import java.util.List;

@Entity(name="doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor{
    @Id
    @GenericGenerator(name = "doctor_id", strategy = "com.example.emantrana.IdGenerator.DoctorGenerator")
    @GeneratedValue(generator = "doctor_id")
    private Long id;
    @Column(nullable = false)
    private String Fname;

    private String Lname;

    @Column(unique = true, nullable = false)
    private String email; // username of doctor

    @Column(nullable = false)
    private String Type;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int patientCount;

    private String resetToken;

    @Column(name = "roles",nullable = false)
    private String roles = "ROLE_DOCTOR";

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DoctorTimeTable> doctorTimeTable;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Appointment> appointment;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Prescription> prescription;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<General_Queue> general_queue;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Doctor_Patient> doctor_patient;

    public Doctor(Long id) {
        this.id = id;
    }

    public Doctor(String fname, String lname, String email, String type, String password, String phone, int patientCount, String roles) {
        this.Fname = fname;
        this.Lname = lname;
        this.email = email;
        this.Type = type;
        this.password = password;
        this.phone = phone;
        this.patientCount = patientCount;
        this.roles = roles;
    }

    public Doctor(
            @JsonProperty("phone")String phone,
            @JsonProperty("password")String password,
            @JsonProperty("Fname")String Fname,
            @JsonProperty("type")String Type,
            @JsonProperty("Lname")String Lname,
            @JsonProperty("email")String email,
            @JsonProperty("dateOfBirth")String dateOfBirth,
            @JsonProperty("patientCount")int patientCount,
            @JsonProperty("id")Long id,
            @JsonProperty("roles")String roles) {

        this.phone = phone;
        this.password = password;
        this.Fname = Fname;
        this.Type = Type;
        this.Lname = Lname;
        this.email = email;
        this.patientCount = patientCount;
        this.roles = roles;
        this.id =id;
    }

}
