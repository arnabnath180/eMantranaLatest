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

import java.util.List;

@Entity(name="patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GenericGenerator(name = "patient_id", strategy = "com.example.emantrana.IdGenerator.PatientGenerator")
    @GeneratedValue(generator = "patient_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String phoneNumber; // userId of patient

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = false)
    private String Fname;


    private String Lname;
    private String email;

    @Column(name="date_of_birth", nullable = false)
    private String dateOfBirth; // should it be string or date data type??

    @Column(nullable = false, unique = false)
    private String Gender;

    @Column(name = "roles",nullable = false)
    private String roles="ROLE_PATIENT";

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Appointment> appointment;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Prescription> prescription;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MedicalRecords> medicalRecords;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<General_Queue> general_queue;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Doctor_Patient> doctor_patient;

    public Patient(
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

    public Patient(Long id) {
        this.id = id;
    }
}
