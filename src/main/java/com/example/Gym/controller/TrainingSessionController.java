package com.example.Gym.controller;

import com.example.Gym.model.TrainingSession;
import com.example.Gym.repository.TrainingSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/session")
public class TrainingSessionController {

    private final TrainingSessionRepository trainingSessionRepository;

    public TrainingSessionController(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sessions", trainingSessionRepository.findAll());
        model.addAttribute("sessionForm", new TrainingSession());
        return "session";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("sessions", trainingSessionRepository.findAll());
        model.addAttribute("sessionForm", trainingSessionRepository.findById(id).orElse(new TrainingSession()));
        return "session";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("sessionForm") TrainingSession session) {
        trainingSessionRepository.save(session);
        return "redirect:/sessions";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("sessionForm") TrainingSession session) {
        session.setId(id);
        trainingSessionRepository.save(session);
        return "redirect:/sessions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        trainingSessionRepository.deleteById(id);
        return "redirect:/sessions";
    }
}
