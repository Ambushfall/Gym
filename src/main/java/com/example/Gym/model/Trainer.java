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

    @ManyToMany(mappedBy = "likedTrainers")
    private List<Member> likedByMembers;

    @OneToOne(mappedBy = "trainer")
    private AppUser appUser;

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

    public List<Member> getLikedByMembers() {
        return likedByMembers;
    }

    public void setLikedByMembers(List<Member> likedByMembers) {
        this.likedByMembers = likedByMembers;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
