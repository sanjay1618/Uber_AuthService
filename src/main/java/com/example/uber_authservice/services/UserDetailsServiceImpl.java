package com.example.uber_authservice.services;

import com.example.uber_authservice.helpers.AuthUserDetails;
import com.example.uber_authservice.models.Passenger;
import com.example.uber_authservice.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PassengerRepository passengerRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findByEmail(username);

        logger.info("Attempting to load user by email: {}", username);
        if (passengerRepository == null) {
            logger.error("PassengerRepository is null!");
            throw new IllegalStateException("PassengerRepository is not injected!");
        }


       if(passenger.get() != null) {
           return new AuthUserDetails(passenger.get());
       }
       else{
           throw new UsernameNotFoundException("User not found with username: " + username);
       }
    }
}
