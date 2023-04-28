package com.example.emantrana.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="general_queue")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class General_Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: Forign key pointig to patient table
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    //TODO: Forign key pointig to doctor table
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    private String status;

    public General_Queue(Patient patient, Doctor doctor, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
    }
}
