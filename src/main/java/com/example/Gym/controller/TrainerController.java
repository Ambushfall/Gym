package com.example.Gym.controller;

import com.example.Gym.model.Trainer;
import com.example.Gym.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerRepository TrainerRepository;

    @GetMapping
    public List<Trainer> getAll() {
        return TrainerRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Trainer create(@RequestBody Trainer t) {
        return TrainerRepository.save(t);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Trainer update(@PathVariable Long id, @RequestBody Trainer t) {
        t.setId(id);
        return TrainerRepository.save(t);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        TrainerRepository.deleteById(id);
    }
}
