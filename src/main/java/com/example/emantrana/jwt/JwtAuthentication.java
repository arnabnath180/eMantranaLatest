package com.example.emantrana.jwt;

import com.example.emantrana.models.Patient;
import com.example.emantrana.patient.dtos.patientRegisterResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {
    private String jwtString;
    private Object user; // change 1 in this file

   // private Patient patient; // this line is changed with patientRegisterResponseDTO

    public JwtAuthentication(String jwtString) {

        this.jwtString = jwtString;
    }
    void setUser(Object user) {

        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: not needed for now,unless we have different resources accessible to differrnt roles
        return null;
    }

    @Override
    public String getCredentials() {
        //This is where we return the string/token that is used for authentication
        return jwtString;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        // This is where we return the user/client who is getting authenticated
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return (user != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
// when it only contains jwt string than it is not authenticated
// when it contains user object than it is authenticated