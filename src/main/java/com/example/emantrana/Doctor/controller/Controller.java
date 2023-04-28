package com.example.emantrana.Doctor.controller;

import com.example.emantrana.Doctor.dto.*;
import com.example.emantrana.Doctor.service.DoctorService;
import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.DoctorTimeTable;
import com.example.emantrana.models.Patient;
import com.example.emantrana.patient.dtos.loginPatientDTO;
import com.example.emantrana.patient.dtos.loginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class Controller {

    private DoctorService doctorService;
    public Controller(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/login")

    public ResponseEntity<DoctorloginResponseDTO> Doctor(
            @RequestBody DoctorLoginDTO request
    ) {

        DoctorloginResponseDTO verifiedDoctor = doctorService.verifyDoctor(request);
        return ResponseEntity.ok().body(verifiedDoctor);
    }

    @PostMapping("/forgot_password")
    public String forgotPasswordResponse(@RequestBody ForgotPasswordDTO request){
        return doctorService.forgetPassword(request);
    }

    @PatchMapping("/reset_password")
    public String resetPasswordResponse(@RequestBody ForgetPasswordRequestDTO request){
        return doctorService.resetPassword(request);
    }

    @GetMapping("/profile")
    public ResponseEntity<Doctor> getPatientProfile(@AuthenticationPrincipal Doctor doctor) {
        Doctor doctor1 = doctorService.findDoctorByemail(doctor.getEmail());
        return ResponseEntity.ok().body(doctor1);
    }

    @PostMapping("/uploadprescription")
    public String uploadPrescription(@RequestBody UploadPrescriptionDTO request){
        return doctorService.uploadPrescription(request);
    }


}
