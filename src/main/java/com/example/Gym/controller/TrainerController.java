package com.example.Gym.controller;

import com.example.Gym.model.Trainer;
import com.example.Gym.repository.TrainerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerRepository trainerRepository;

    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("trainers", trainerRepository.findAll());
        model.addAttribute("trainerForm", new Trainer());
        return "trainer";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("trainers", trainerRepository.findAll());
        model.addAttribute("trainerForm", trainerRepository.findById(id).orElse(new Trainer()));
        return "trainer";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String add(@ModelAttribute("trainerForm") Trainer trainer) {
        trainerRepository.save(trainer);
        return "redirect:/trainers";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@PathVariable Long id, @ModelAttribute("trainerForm") Trainer trainer) {
        trainer.setId(id);
        trainerRepository.save(trainer);
        return "redirect:/trainers";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        trainerRepository.deleteById(id);
        return "redirect:/trainers";
    }
}
