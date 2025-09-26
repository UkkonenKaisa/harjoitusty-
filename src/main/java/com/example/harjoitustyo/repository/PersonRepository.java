package com.example.harjoitustyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.harjoitustyo.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.weightMeasurements WHERE p.id = :id")
    Optional<Person> findByIdWithWeightMeasurements(@Param("id") Long id);
}
