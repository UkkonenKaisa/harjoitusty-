package com.example.harjoitustyo.config;

import com.example.harjoitustyo.model.Hobby;
import com.example.harjoitustyo.repository.HobbyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initHobbies(HobbyRepository hobbyRepository) {
        return args -> {
            if (hobbyRepository.count() == 0) {
                hobbyRepository.save(new Hobby("Laskettelu"));
                hobbyRepository.save(new Hobby("Jalkapallo"));
                hobbyRepository.save(new Hobby("Lukeminen"));
                hobbyRepository.save(new Hobby("Musiikki"));
                hobbyRepository.save(new Hobby("Kuntosali"));
                hobbyRepository.save(new Hobby("Uinti"));
                hobbyRepository.save(new Hobby("Juoksu"));
                hobbyRepository.save(new Hobby("Pyöräily"));
                hobbyRepository.save(new Hobby("Jooga"));
                hobbyRepository.save(new Hobby("Kalastus"));
                hobbyRepository.save(new Hobby("Metsästys"));
                hobbyRepository.save(new Hobby("Käsityöt"));
                hobbyRepository.save(new Hobby("Elokuvat"));
                hobbyRepository.save(new Hobby("Ruuanlaitto"));
                hobbyRepository.save(new Hobby("Puutarhanhoito"));
                System.out.println("✅ Lisättiin oletusharrastukset tietokantaan.");
            }
        };
    }
}
