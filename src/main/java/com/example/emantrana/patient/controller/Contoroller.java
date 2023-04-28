package com.example.emantrana.patient.controller;

import com.example.emantrana.patient.dtos.GetDoctorResponseDTO;
import com.example.emantrana.models.Patient;
import com.example.emantrana.patient.dtos.*;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.emantrana.patient.service.patientService;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.core.io.InputStreamResource;

@CrossOrigin
@RestController
@RequestMapping("/patient/")
public class Contoroller {
   private patientService patientService;

    public Contoroller(patientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<patientRegisterResponseDTO> registerPatient(@RequestBody registerPatientDTO request) {
        patientRegisterResponseDTO createdPatient = patientService.registerPatient(request);
        return ResponseEntity.created(URI.create("/patient/")).body(createdPatient);
    }

    @PostMapping("/login")
    public ResponseEntity<loginResponseDTO> verifyPatient(
            @RequestBody loginPatientDTO request
    ) {
        loginResponseDTO verifiedPatient = patientService.verifyPatient(request);
        return ResponseEntity.ok().body(verifiedPatient);
    }

    @GetMapping("/profile")
    public ResponseEntity<Patient> getPatientProfile(@AuthenticationPrincipal Patient patient) {
        Patient patient1 = patientService.findPatientByPhoneNumber(patient.getPhoneNumber());
        return ResponseEntity.ok().body(patient1);
    }

    @PatchMapping("/updatePatientPassword")
    public String UpdatePatientPassword(@RequestBody updatePasswordDTO request) {
        String message = patientService.updatePatientPassword(request);
        return message;
    }

    @PostMapping("/getall")
    public List<GetDoctorResponseDTO> getAll(@RequestBody AvailableDoctorRequestDTO availableDoctorRequestDTO){
        return patientService.getAll(availableDoctorRequestDTO);
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<?> uploadMedicalRecord(@RequestParam("image") MultipartFile request,
                                                 @AuthenticationPrincipal Patient patient) throws IOException {
        patientService.uploadMedicalRecord(request, patient);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/medicalRecords")
    public ResponseEntity<?> getAllMedicalRecordName(@AuthenticationPrincipal Patient patient) throws IOException {
        List<String> medicalRecords = patientService.getAllMedicalRecordsName(patient);
        return ResponseEntity.ok().body(medicalRecords);
    }
    @GetMapping("/medicalRecords/{fileName}")
    public ResponseEntity<?> downloadImageFromSystem(@PathVariable String fileName) throws IOException {
        byte [] imageData = patientService.downloadMedicalRecord(fileName);
        byte[] base64encodedData = Base64.getEncoder().encode(imageData);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

    @GetMapping("/getPrescription")
    public ResponseEntity<InputStreamResource> createPdf(@AuthenticationPrincipal Patient patient) throws IOException, MessagingException {
        ByteArrayInputStream pdf = patientService.createPdf(patient);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=hello.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @PatchMapping ("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO request,
                                           @AuthenticationPrincipal Patient patient) {
        patientService.updateProfile(request, patient);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deleteRecord/{filename}")
    public ResponseEntity<?> deleteRecord(@PathVariable String filename, @AuthenticationPrincipal Patient patient) {
        String status = patientService.deleteRecord(filename, patient);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
