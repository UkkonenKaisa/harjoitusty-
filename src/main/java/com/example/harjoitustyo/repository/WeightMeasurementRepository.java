package com.example.harjoitustyo.repository;

import com.example.harjoitustyo.model.WeightMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightMeasurementRepository extends JpaRepository<WeightMeasurement, Long> {
}
