package com.example.Gym.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String membershipType;

    @OneToMany(mappedBy = "member")
    private List<TrainingSession> sessions;

    public Member() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public List<TrainingSession> getSessions() { return sessions; }
    public void setSessions(List<TrainingSession> sessions) { this.sessions = sessions; }
}