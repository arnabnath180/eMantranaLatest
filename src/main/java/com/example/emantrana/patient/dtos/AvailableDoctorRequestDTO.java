package com.example.emantrana.patient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableDoctorRequestDTO {
    Long id;
    String type;

    public AvailableDoctorRequestDTO(@JsonProperty("id") Long id,
                                     @JsonProperty("type") String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
