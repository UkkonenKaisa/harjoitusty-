package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.model.Address;
import com.example.harjoitustyo.repository.AddressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressRepository repo;

    public AddressController(AddressRepository repo) {
        this.repo = repo;
    }

    // READ all
    @GetMapping
    public List<Address> getAll() {
        return repo.findAll();
    }

    // READ by id
    @GetMapping("/{id}")
    public Optional<Address> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    // CREATE
    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return repo.save(address);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        address.setId(id);
        return repo.save(address);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
