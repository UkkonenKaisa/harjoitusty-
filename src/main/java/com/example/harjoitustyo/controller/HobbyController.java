package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.model.Hobby;
import com.example.harjoitustyo.repository.HobbyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hobbies")
public class HobbyController {

    private final HobbyRepository repo;

    public HobbyController(HobbyRepository repo) {
        this.repo = repo;
    }

    // READ all
    @GetMapping
    public List<Hobby> getAll() {
        return repo.findAll();
    }

    // READ by id
    @GetMapping("/{id}")
    public Optional<Hobby> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    // CREATE
    @PostMapping
    public Hobby addHobby(@RequestBody Hobby hobby) {
        return repo.save(hobby);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Hobby updateHobby(@PathVariable Long id, @RequestBody Hobby hobby) {
        hobby.setId(id);
        return repo.save(hobby);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteHobby(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
