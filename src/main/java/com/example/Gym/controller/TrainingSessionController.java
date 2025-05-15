package com.example.Gym.controller;

import com.example.Gym.model.TrainingSession;
import com.example.Gym.repository.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api/sessions")
public class TrainingSessionController {
    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @GetMapping
    public List<TrainingSession> getAll() {
        return trainingSessionRepository.findAll();
    }

    @PostMapping
    public TrainingSession create(@RequestBody TrainingSession session) {
        return trainingSessionRepository.save(session);
    }

    @PutMapping("/{id}")
    public TrainingSession update(@PathVariable Long id, @RequestBody TrainingSession session) {
        session.setId(id);
        return trainingSessionRepository.save(session);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trainingSessionRepository.deleteById(id);
    }
}