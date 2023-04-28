package com.example.emantrana.patient.service;

import com.example.emantrana.GeneralQueue.dto.DoctorRejectedDTO;
import com.example.emantrana.models.*;
import com.example.emantrana.patient.dtos.GetDoctorResponseDTO;
import com.example.emantrana.patient.dtos.*;
import com.example.emantrana.repository.*;
import com.example.emantrana.util.ImageUtils;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.emantrana.jwt.JwtService;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

@Service
public class patientService {
    private PatientRepo patientRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private PatientMedicalRecordsRepo patientMedicalRecordsRepo;
    private PatientPrescriptionRepo patientPrescriptionRepo;

    @PersistenceContext
    private EntityManager entityManager;

    private final String folderpath = "/home/arnab/teleconsulting/public/assests";

    @Autowired
    private DoctorRepo doctorRepository;
    @Autowired
    private DoctorGeneral_QueueRepo gqr;

    public patientService(PatientRepo patientRepo, PatientMedicalRecordsRepo patientMedicalRecordsRepo,PatientPrescriptionRepo patientPrescriptionRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.patientRepo = patientRepo;
        this.patientMedicalRecordsRepo = patientMedicalRecordsRepo;
        this.patientPrescriptionRepo = patientPrescriptionRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public patientRegisterResponseDTO registerPatient(registerPatientDTO request) {
        Patient patient = modelMapper.map(request, Patient.class);
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        Patient savedPatient = patientRepo.save(patient);
        patientRegisterResponseDTO response = modelMapper.map(savedPatient, patientRegisterResponseDTO.class);
        return response;
    }

    public loginResponseDTO verifyPatient(loginPatientDTO request) {
         Patient patient = patientRepo.findByPhoneNumber(request.getPhoneNumber());
         if(patient == null) {
             throw new RuntimeException("Patient not found");
         }
         if(!passwordEncoder.matches(request.getPassword(), patient.getPassword())) {
             throw new RuntimeException("Invalid password");
         }
         String token = jwtService.createJwt(patient.getPhoneNumber(), patient.getRoles());
         loginResponseDTO response = new loginResponseDTO();
         response.setToken(token);
         response.setPatientId(patient.getId());
         return response;
    }

    public Patient findPatientByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepo.findByPhoneNumber(phoneNumber);
        if(patient == null) {
            throw new RuntimeException("Patient not found");
        }
       // patientRegisterResponseDTO response = modelMapper.map(patient, patientRegisterResponseDTO.class);
        return patient;
        // here we're first returning response and then returning patient
    }

    public patientRegisterResponseDTO getPatientInfo(Patient patient) {
        return modelMapper.map(patient, patientRegisterResponseDTO.class);
    }

    public String updatePatientPassword(updatePasswordDTO request) {
        Patient patient = patientRepo.findByPhoneNumber(request.getPhoneNumber());
        if(patient == null) {
            throw new RuntimeException("Patient not found");
        }
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patientRepo.save(patient);
        return "Password updated successfully";
    }

    public List<GetDoctorResponseDTO> getAll(AvailableDoctorRequestDTO availableDoctorRequestDTO) {
        Long id= availableDoctorRequestDTO.getId();
        String type= availableDoctorRequestDTO.getType();
        ArrayList<String> days=new ArrayList<String>();
        days.add(null);
        days.add("sunday");
        days.add("monday");
        days.add("tuesday");
        days.add("wednesday");
        days.add("thursday");
        days.add("friday");
        days.add("saturday");
        List<GetDoctorResponseDTO> docs=new ArrayList<>();
        LocalTime now=LocalTime.now();
        Calendar calendar = Calendar.getInstance();
        String day = days.get(calendar.get(Calendar.DAY_OF_WEEK));
        List<Doctor> doctors = new ArrayList<>();
        doctors=doctorRepository.getAll(type);
        List<GetDoctorResponseDTO> response = new ArrayList<>();
        List<Long> docRejected = gqr.getDoctorRejected(id,"rejected");
        for(Doctor d : doctors){
            for(DoctorTimeTable t: d.getDoctorTimeTable()){
                LocalTime t_in=LocalTime.parse(t.getTime_in().toString());
                LocalTime t_out=LocalTime.parse(t.getTime_out().toString());
                if((t.getDay()).equals(day) && t_in.isBefore(now) && t_out.isAfter(now)){
                    GetDoctorResponseDTO doc=new GetDoctorResponseDTO(d.getId(), d.getFname(),d.getLname(),
                            d.getType(),d.getEmail(),d.getPhone());
                    response.add(doc);
                }
            }
        }
        for (Long i:docRejected){
            for(GetDoctorResponseDTO j:response){
                if(i==j.getId()){
                    response.remove(j);
                    break;
                }
            }
        }
        return response;
    }


    public String uploadMedicalRecord(MultipartFile request, Patient patient) throws IOException {
        String filePath = folderpath + request.getOriginalFilename();
        Patient p = patientRepo.findByPhoneNumber(patient.getPhoneNumber());

        MedicalRecords fileData = patientMedicalRecordsRepo.save(MedicalRecords.builder()
                        .fileName(request.getOriginalFilename())
                        .patient(p)
                .recordImageLink(filePath)
                .build());

        ImageUtils.compressImage(request.getBytes());
        request.transferTo(new File(filePath));

        if(fileData == null) {
            throw new RuntimeException("File not uploaded");
        }
        return "File uploaded successfully";
    }
    public byte[] downloadMedicalRecord(String fileName) throws IOException {
        Optional<MedicalRecords> medicalRecord = patientMedicalRecordsRepo.findByFileName(fileName);
        if(!medicalRecord.isPresent()) {
            throw new RuntimeException("File not found");
        }
        String filepath = medicalRecord.get().getRecordImageLink();
        System.out.println(filepath);
        byte[] image = Files.readAllBytes(new File(filepath).toPath());
        return image;
    }

    public List<String> getAllMedicalRecordsName(Patient patient) {
        List<MedicalRecords> medicalRecords = patientMedicalRecordsRepo.findByPatientId(patient.getId());
        List<String> fileNames = new ArrayList<>();
        for(MedicalRecords medicalRecord : medicalRecords) {
            fileNames.add(medicalRecord.getFileName());
        }
        return fileNames;
    }

    public ByteArrayInputStream createPdf(Patient patient) {
        Prescription prescription = patientPrescriptionRepo.findByPatientId(patient.getId());
        String title = "Welcome to eMantrana";
        String content = "This prescription is generated by eMantrana";

        Map<String, String> map = new HashMap<>();
        map.put("Patient Name : ",patient.getFname()+" "+patient.getLname());
        map.put("Doctor Name : ",prescription.getDoctor().getFname()+" "+prescription.getDoctor().getLname());
        map.put("Date : ",String.valueOf(prescription.getPreseciptionDate()));
        map.put("Day : ",prescription.getDay());
        map.put("Medicine : ",prescription.getMedicine());
        map.put("Description : ",prescription.getDescription());


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();
        try {

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD);
            Paragraph titleParagraph = new Paragraph(title, titleFont);
            titleParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(titleParagraph);

            Font paraFont = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL);
            Paragraph paragraph = new Paragraph(content, paraFont);
            document.add(paragraph);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();


            try {

                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
                Paragraph titleParagraph = new Paragraph(key, titleFont);
                titleParagraph.setAlignment(Element.ALIGN_LEFT);
                document.add(titleParagraph);

                Font paraFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL);
                Paragraph paragraph = new Paragraph(value, paraFont);
                document.add(paragraph);

            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public String getImagePath(String fileName) {
        Optional<MedicalRecords> medicalRecord = patientMedicalRecordsRepo.findByFileName(fileName);
        if(!medicalRecord.isPresent()) {
            throw new RuntimeException("File not found");
        }
        String filepath = medicalRecord.get().getRecordImageLink();
        return filepath;
    }

    public void updateProfile(UpdateProfileDTO request, Patient patient) {
        Patient p = modelMapper.map(request, Patient.class);
        p.setId(patient.getId());
        patientRepo.save(p);
    }


    public String deleteRecord(String filename, Patient patient) {
        Optional<MedicalRecords> medicalRecord = patientMedicalRecordsRepo.findByPatientIdAndAndFileName(patient.getId(), filename);
        if(!medicalRecord.isPresent()) {
            throw new RuntimeException("File not found");
        }
        String filepath = medicalRecord.get().getRecordImageLink();
        patientMedicalRecordsRepo.delete(medicalRecord.get());
        // delete file from folder
        File file = new File(filepath);
        if(!file.delete()) {
            throw new RuntimeException("File not deleted");
        }
        return "File deleted successfully";
    }
}
