package com.example.Gym.controller;

import com.example.Gym.model.Feedback;
import com.example.Gym.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @PostMapping
    public Feedback create(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @PutMapping("/{id}")
    public Feedback update(@PathVariable Long id, @RequestBody Feedback feedback) {
        feedback.setId(id);
        return feedbackRepository.save(feedback);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        feedbackRepository.deleteById(id);
    }
}

