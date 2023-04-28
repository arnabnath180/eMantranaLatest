package com.example.emantrana.models;

import lombok.*;

import javax.persistence.*;

@Entity(name="medical_records")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MedicalRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, nullable = false)
    private String recordImageLink;
    private String fileName;

    // Forign key pointig to patient table
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;
}
