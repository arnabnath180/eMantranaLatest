package com.example.emantrana.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity(name="doctor_time_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorTimeTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String day;

    @Column(nullable = false)
    private java.sql.Time time_in;

    @Column(nullable = false)
    private java.sql.Time time_out;

    // Forign key pointig to doctor table
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    //TODO: Forign key pointig to doctor table


    public DoctorTimeTable(
            @JsonProperty("day") String day,
            @JsonProperty("time_in") Time time_in,
            @JsonProperty("time_out") Time time_out, Doctor doctor) {
        this.day = day;
        this.time_in = time_in;
        this.time_out = time_out;
        this.doctor = doctor;
    }
}
