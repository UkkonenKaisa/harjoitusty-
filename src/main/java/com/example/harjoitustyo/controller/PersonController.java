package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.model.Person;
import com.example.harjoitustyo.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    // READ all
    @GetMapping
    public List<Person> getAll() {
        return repo.findAll();
    }

    // READ by id
    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    // CREATE
    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return repo.save(person);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        person.setId(id);
        return repo.save(person);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
