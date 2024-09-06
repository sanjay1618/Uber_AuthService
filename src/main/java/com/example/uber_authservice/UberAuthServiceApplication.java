package com.example.uber_authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EntityScan("com.example.uberprojectentityservice.models")
public class UberAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberAuthServiceApplication.class, args);
    }

}
