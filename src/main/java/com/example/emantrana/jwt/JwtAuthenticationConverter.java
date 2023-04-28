package com.example.emantrana.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class JwtAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization"); // we need to get auth header from request
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // we need to get token from auth header
            return new JwtAuthentication(token); // we need to create authentication object from token and send it to AuthenticationManager
        }
        return null;
    }
}
// if converter passes than auth filter will check whether authentication is supposed to happening or not
// so now authentication object will be passed to authentication manager
