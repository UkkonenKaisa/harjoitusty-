package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.model.WeightMeasurement;
import com.example.harjoitustyo.repository.WeightMeasurementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weights")
public class WeightMeasurementController {

    private final WeightMeasurementRepository repo;

    public WeightMeasurementController(WeightMeasurementRepository repo) {
        this.repo = repo;
    }

    // READ all
    @GetMapping
    public List<WeightMeasurement> getAll() {
        return repo.findAll();
    }

    // READ by id
    @GetMapping("/{id}")
    public Optional<WeightMeasurement> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    // CREATE
    @PostMapping
    public WeightMeasurement addMeasurement(@RequestBody WeightMeasurement wm) {
        return repo.save(wm);
    }

    // UPDATE
    @PutMapping("/{id}")
    public WeightMeasurement updateMeasurement(@PathVariable Long id, @RequestBody WeightMeasurement wm) {
        wm.setId(id);
        return repo.save(wm);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteMeasurement(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
