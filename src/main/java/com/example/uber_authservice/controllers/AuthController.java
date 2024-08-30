package com.example.uber_authservice.controllers;

import com.example.uber_authservice.DTOs.PassengerSignupRequestDTO;
import com.example.uber_authservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signUp(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){

       return ResponseEntity.status(HttpStatus.CREATED).body(authService.signupPassenger(passengerSignupRequestDTO));
    }
}
