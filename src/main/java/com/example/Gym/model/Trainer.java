package com.example.Gym.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;

    @OneToMany(mappedBy = "trainer")
    private List<TrainingSession> sessions;

    // --- Konstruktor bez argumenata (obavezan za JPA) ---
    public Trainer() {
    }

    // --- Getteri i setteri ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TrainingSession> sessions) {
        this.sessions = sessions;
    }
}