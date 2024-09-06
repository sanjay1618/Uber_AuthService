package com.example.uber_authservice.services;

import com.example.uber_authservice.DTOs.PassengerDTO;
import com.example.uber_authservice.DTOs.PassengerSignupRequestDTO;

import com.example.uber_authservice.repository.PassengerRepository;
import com.example.uberprojectentityservice.models.Passenger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passengerRepository = passengerRepository;
    }

    public PassengerDTO signupPassenger(PassengerSignupRequestDTO passengerSignupRequestDTO){
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDTO.getEmail())
                .phoneNumber(passengerSignupRequestDTO.getPhoneNumber())
                .name(passengerSignupRequestDTO.getName())
                .password(bCryptPasswordEncoder.encode(passengerSignupRequestDTO.getPassword()))
                .build();
        Passenger newPassenger = passengerRepository.save(passenger);
        return PassengerDTO.fromPassenger(newPassenger);

    }
}
