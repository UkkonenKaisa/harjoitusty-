package com.example.harjoitustyo.repository;

import com.example.harjoitustyo.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
