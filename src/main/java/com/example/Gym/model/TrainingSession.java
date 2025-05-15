package com.example.Gym.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime time;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    private Member member;

    public TrainingSession() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
}
