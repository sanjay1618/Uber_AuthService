package com.example.uber_authservice.services;

import com.example.uber_authservice.helpers.AuthUserDetails;
import com.example.uber_authservice.models.Passenger;
import com.example.uber_authservice.repository.PassengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PassengerRepository passengerRepository;

    public UserDetailsServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Passenger passenger = passengerRepository.findByEmail(username);
       if(passenger != null) {
           return new AuthUserDetails(passenger);
       }
       else{
           throw new UsernameNotFoundException("User not found with username: " + username);
       }
    }
}
