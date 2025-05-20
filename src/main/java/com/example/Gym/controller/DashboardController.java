package com.example.Gym.controller;

import com.example.Gym.model.*;
import com.example.Gym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class DashboardController {

    @Autowired
    private UserRepository appUserRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainingSessionRepository trainingSessionRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/{role}/dashboard")
    public String dashboard(@PathVariable String role, Principal principal, Model model) {
        model.addAttribute("role", role.toUpperCase());
        model.addAttribute("username", principal.getName());

        switch (role.toUpperCase()) {
            case "MEMBER": {
                model.addAttribute("trainers", trainerRepository.findAll());
            };
            break;
            case "ADMIN":{
                model.addAttribute("users", appUserRepository.findAll());
                model.addAttribute("members", memberRepository.findAll());
                model.addAttribute("trainers", trainerRepository.findAll());
                model.addAttribute("sessions", trainingSessionRepository.findAll());
                model.addAttribute("feedbacks", feedbackRepository.findAll());

                // Prazni form objekti
                model.addAttribute("userForm", new AppUser());
                model.addAttribute("memberForm", new Member());
                model.addAttribute("trainerForm", new Trainer());
                model.addAttribute("sessionsForm", new TrainingSession());
                model.addAttribute("feedbackForm", new Feedback());
            };
            break;
            case "TRAINER":{
                model.addAttribute("sessions", trainingSessionRepository.findAll());
            };
            break;
        }

        // Ostale role (trainer, member) možeš dopuniti isto ovde ako želiš
        return "shared-dashboard";


    }
}
