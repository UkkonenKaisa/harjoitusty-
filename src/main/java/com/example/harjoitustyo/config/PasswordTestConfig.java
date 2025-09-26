package com.example.harjoitustyo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordTestConfig {

    @Bean
    public CommandLineRunner printHashedPasswords(PasswordEncoder encoder) {
        return args -> {
            System.out.println("Salattu 'user': " + encoder.encode("user"));
            System.out.println("Salattu 'admin': " + encoder.encode("admin"));
        };
    }
}
