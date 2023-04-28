package com.example.emantrana.models;

import javax.persistence.*;

@Entity(name="appointment")
public class Appointment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) // should it be unique or not
    private String AppointmentIn;

    @Column(nullable = false)
    private String AppointmentOut;

    //TODO: Forign key pointig to doctor table
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    //TODO: Forign key pointig to patient table
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;
}
