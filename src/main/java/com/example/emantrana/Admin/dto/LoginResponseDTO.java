package com.example.emantrana.Admin.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String Admintoken;
    Long AdminId;
    boolean Status=true;

    public LoginResponseDTO(String Admintoken) {
        this.Admintoken = Admintoken;
    }

    public LoginResponseDTO() {

    }

    public String getToken() {
        return Admintoken;
    }

    public void setToken(String Admintoken) {
        this.Admintoken = Admintoken;
    }
}
