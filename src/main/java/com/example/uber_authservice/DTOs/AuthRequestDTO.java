package com.example.uber_authservice.DTOs;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthRequestDTO {

    private String email;
    private String password;
}
