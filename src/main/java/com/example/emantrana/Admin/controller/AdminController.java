package com.example.emantrana.Admin.controller;

import com.example.emantrana.Admin.dto.*;
import com.example.emantrana.Admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> verifyPatient(
            @RequestBody LoginAdminDTO request)
    {
        LoginResponseDTO verifiedAdmin = adminService.verifyAdmin(request);
        return ResponseEntity.ok().body(verifiedAdmin);
    }
    @PostMapping(value = "/add_doctor")
    public void addDoctor(@RequestBody DoctorRegistrationRequestDTO doctor){
        adminService.addDoctor(doctor);
    }
    @GetMapping(value = "/get_doctor/{email}")
    public GetDoctorResponseDTO getDoctor(@PathVariable("email") String email){
        GetDoctorRequestDTO getDoctorRequestDTO=new GetDoctorRequestDTO(email);
        GetDoctorResponseDTO getDoctorResponseDTO=adminService.getDoctor(getDoctorRequestDTO);
        return getDoctorResponseDTO;
    }

    @PutMapping (value = "/update_doctor")
    public void updateDoctor(@RequestBody DoctorUpdateDTO doctor){
        adminService.updateDoctor(doctor);
    }

    @DeleteMapping  (value = "/delete_doctor/{email}")
    public void updateDoctor(@PathVariable("email") String email){
        GetDoctorRequestDTO getDoctorRequestDTO=new GetDoctorRequestDTO(email);
        adminService.deleteDoctor(getDoctorRequestDTO);
    }
}
