package com.example.emantrana.jwt;

import com.example.emantrana.Admin.service.AdminService;
import com.example.emantrana.Doctor.service.DoctorService;
import com.example.emantrana.patient.service.patientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private patientService PatientService;
    private DoctorService doctorService;
    private AdminService adminService;

    public AppSecurityConfig(
            JwtService jwtService,
            patientService PatientService,
            DoctorService doctorService,
            AdminService adminService
    ) {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(
                new JwtAuthenticationManager(
                        jwtService, PatientService, doctorService, adminService
                )
        );

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/","/patient/medicalRecords/{fileName}").permitAll()
                .antMatchers(HttpMethod.POST,"/patient/register","/patient/login","/doctor/login","/admin/login").permitAll()
                .antMatchers(HttpMethod.PATCH,"/patient/updatePatientPassword","/doctor/reset_password").permitAll()
                .antMatchers("/patient/**","/admin/**").authenticated()
                .antMatchers("/doctor/**").authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                //.addFilterBefore(doctorAuthFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
        // super.configure(http);
    }
}
