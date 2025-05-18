package com.example.Gym.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @OneToMany(mappedBy = "trainer")
    private List<TrainingSession> sessions;

    @OneToOne
    private AppUser appUser; // ✅ Dodato za povezivanje sa login korisnikom

    public Trainer() {
    }

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TrainingSession> sessions) {
        this.sessions = sessions;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
