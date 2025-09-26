package com.example.harjoitustyo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "hobbies")
    @JsonBackReference   // tai vaihtoehtoisesti @JsonIgnore
    private Set<Person> persons = new HashSet<>();
    // --- Konstruktorit ---
    public Hobby() {
    }

    public Hobby(String name) {
        this.name = name;
    }

    // Getterit ja setterit
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Person> getPersons() { return persons; }
    public void setPersons(Set<Person> persons) { this.persons = persons; }
}
