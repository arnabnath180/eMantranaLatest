package com.example.emantrana.GeneralQueue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteQueueDTO {
    Long id;

    public DeleteQueueDTO(@JsonProperty("id") Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
