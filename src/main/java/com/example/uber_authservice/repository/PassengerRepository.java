package com.example.uber_authservice.repository;

import com.example.uber_authservice.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    Passenger findByEmail(String email);
}
