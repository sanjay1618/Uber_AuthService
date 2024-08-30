package com.example.uber_authservice.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerSignupRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
