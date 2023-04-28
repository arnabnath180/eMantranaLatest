package com.example.emantrana.Admin.service;

import com.example.emantrana.Admin.dto.*;
import com.example.emantrana.jwt.JwtService;
import com.example.emantrana.models.Admin;
import com.example.emantrana.models.Doctor;
import com.example.emantrana.models.DoctorTimeTable;
import com.example.emantrana.repository.AdminRepo;
import com.example.emantrana.repository.DoctorRepo;
import com.example.emantrana.repository.DoctorTimeTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DoctorRepo doctorRepository;
    @Autowired
    private DoctorTimeTableRepo doctimeRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private JwtService jwtService;
    public LoginResponseDTO verifyAdmin(LoginAdminDTO request) {
        Admin admin = adminRepo.findByEmail(request.getEmail());
        if(admin == null) {
            throw new RuntimeException("Admin not found");
        }
        String token = jwtService.createAdminJwt(admin.getEmail(), admin.getRoles());
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setAdminId(admin.getId());
        return response;
    }
    public void addDoctor(DoctorRegistrationRequestDTO doctor){
        String password="abcd11$";
        Doctor doc=new Doctor(doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getType(), "abcd11$",
                doctor.getPh_number(), 0,"ROLE_DOCTOR");
        doc.setPassword(passwordEncoder.encode(doc.getPassword()));
        doc=doctorRepository.save(doc);
        Long id=doc.getId();
        List<TimeTableDTO> t=doctor.getTimeTable();
        for(Object o:t){
            TimeTableDTO timeTableDTO=(TimeTableDTO) o;
            java.sql.Time tin=new java.sql.Time(timeTableDTO.getTime_in()*1000-(5*3600+30*60)*1000);
            java.sql.Time tout=new java.sql.Time(timeTableDTO.getTime_out()*1000-(5*3600+30*60)*1000);
            DoctorTimeTable timeTable=new DoctorTimeTable(timeTableDTO.getDay(),
                    tin,
                    tout,
                    doc);
            doctimeRepo.save(timeTable);
        }
    }

    public GetDoctorResponseDTO getDoctor(GetDoctorRequestDTO email){
        Doctor doc=doctorRepository.findByEmail(email.getEmail());
        List<DoctorTimeTable> t=doc.getDoctorTimeTable();
        for(DoctorTimeTable i:t){
            i.setDoctor(null);
        }
        GetDoctorResponseDTO response=new GetDoctorResponseDTO(doc.getId(),doc.getFname(), doc.getLname(),
                doc.getType(), doc.getEmail(), doc.getPhone(), doc.getPatientCount(), t);

        return response;
    }



    public void updateDoctor(DoctorUpdateDTO doctor){
        doctorRepository.updateDoctor(doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getPh_number(),
                doctor.getType(), doctor.getId());
        doctimeRepo.deleteByDoctorId(doctor.getId());

        List<TimeTableDTO> t=doctor.getTimeTable();
        Doctor doc=new Doctor(doctor.getId());
        for(Object o:t){
            TimeTableDTO timeTableDTO=(TimeTableDTO) o;
            java.sql.Time tin=new java.sql.Time(timeTableDTO.getTime_in()*1000-(5*3600+30*60)*1000);
            java.sql.Time tout=new java.sql.Time(timeTableDTO.getTime_out()*1000-(5*3600+30*60)*1000);
            DoctorTimeTable timeTable=new DoctorTimeTable(timeTableDTO.getDay(),
                    tin,
                    tout,
                    doc);
            doctimeRepo.save(timeTable);
        }
    }
    public void deleteDoctor(GetDoctorRequestDTO email){
        doctorRepository.deleteByEmail(email.getEmail());
    }

    public Admin findAdminByemail(String email) {
        return adminRepo.findByEmail(email);
    }
}
