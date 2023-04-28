package com.example.emantrana.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class JwtService {
    public static final String SECRET = "hkhr980e790871uiuYIUYRkjedhuiyfjk";
    Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public String createJwt(String phoneNumber, String role) {
        return JWT.create()
                .withSubject(phoneNumber)
                .withClaim("roles",role)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(algorithm);
    }

    public String getPhoneNumberFromJwt(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

    public String createDoctorJwt(String email, String role) {
        return JWT.create()
                .withSubject(email)
                .withClaim("roles",role)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(algorithm);
    }

    public String createAdminJwt(String email, String role) {
        return JWT.create()
                .withSubject(email)
                .withClaim("roles",role)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(algorithm);
    }

    public String getEmailFromJwt(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

    public String getRolesFromJwt(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaim("roles")
                .asString(); // somewhat different form gpt
    }

    // this function get's the subject form jwt token
//    public String getIdentifierFromJwt(String token) {
//        String identifier = null;
//        try {
//            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
//            identifier = decodedJWT.getSubject();
//        } catch (JWTVerificationException e) {
//            // JWT is invalid or has expired
//            throw new RuntimeException("Invalid or expired JWT token");
//        }
//        return identifier;
//    }
//
//    public String getRoleFromJwt(String jwtString) {
//        String role = null;
//        try {
//            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(jwtString);
//            role = decodedJWT.getClaim("roles").asString();
//        } catch (JWTVerificationException e) {
//            // JWT is invalid or has expired
//            throw new RuntimeException("Invalid or expired JWT token");
//        }
//        return role;
//    }
}
