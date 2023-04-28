package com.example.emantrana.Admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableDTO {
    private String day;
    private long time_in;
    private long time_out;

    public TimeTableDTO() {
    }

    public TimeTableDTO(
            @JsonProperty("day") String day,
            @JsonProperty("time_in") long time_in,
            @JsonProperty("time_out") long time_out) {
        this.day = day;
        this.time_in = time_in;
        this.time_out = time_out;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public long getTime_in() {
        return time_in;
    }

    public void setTime_in(long time_in) {
        this.time_in = time_in;
    }

    public long getTime_out() {
        return time_out;
    }

    public void setTime_out(long time_out) {
        this.time_out = time_out;
    }
}
