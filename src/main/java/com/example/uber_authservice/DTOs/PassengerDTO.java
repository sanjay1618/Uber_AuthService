package com.example.uber_authservice.DTOs;
import com.example.uberprojectentityservice.models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PassengerDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private Integer id;
    private Date createdAt;

    public static PassengerDTO fromPassenger(Passenger passenger) {
        return PassengerDTO.builder()
                .createdAt(passenger.getCreatedAt())
                .id(passenger.getId())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber())
                .password(passenger.getPassword())
                .name(passenger.getName())
                .build();
    }


}
