package com.example.emantrana.jwt;

import com.example.emantrana.Admin.service.AdminService;
import com.example.emantrana.models.Admin;
import com.example.emantrana.models.Patient;
import com.example.emantrana.patient.service.patientService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.example.emantrana.Doctor.service.DoctorService;
import com.example.emantrana.models.Doctor;

public class JwtAuthenticationManager implements AuthenticationManager {

    private JwtService jwtService;
    private patientService PatientService;
    private DoctorService doctorService;
    private AdminService adminService;


    public JwtAuthenticationManager(JwtService jwtService, patientService PatientService, DoctorService doctorService, AdminService adminService) {
        this.jwtService = jwtService;
        this.PatientService = PatientService;
        this.doctorService = doctorService;
        this.adminService = adminService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

            String jwtString = jwtAuthentication.getCredentials();
            String role = jwtService.getRolesFromJwt(jwtString);

            if(role.equals("ROLE_PATIENT")){
                String phoneNumber = jwtService.getPhoneNumberFromJwt(jwtString);
                Patient patient = PatientService.findPatientByPhoneNumber(phoneNumber);
                jwtAuthentication.setUser(patient);
            }
            else if(role.equals("ROLE_DOCTOR")){
                String email = jwtService.getEmailFromJwt(jwtString);
                Doctor doctor = doctorService.findDoctorByemail(email);
                jwtAuthentication.setUser(doctor);
            }
            else if(role.equals("ROLE_ADMIN")){
                String email = jwtService.getEmailFromJwt(jwtString);
                Admin admin = adminService.findAdminByemail(email);
                jwtAuthentication.setUser(admin);
            }
            return jwtAuthentication;

        }
        return null;
    }
}

// here we get the authication object
// from the authentication object we find out whether user exists or not
// initially auth object will only contain jwt string(through converter) than when it passed through authentication manager it will contain user object
