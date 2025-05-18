package com.example.Gym.controller;

import com.example.Gym.model.AppUser;
import com.example.Gym.model.Member;
import com.example.Gym.model.Trainer;
import com.example.Gym.model.Role;
import com.example.Gym.repository.UserRepository;
import com.example.Gym.repository.MemberRepository;
import com.example.Gym.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class RoleSelectionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @PostMapping("/choose-role")
    public String setUserRole(@RequestParam("role") String role, Principal principal) {
        AppUser user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (role.equals("MEMBER")) {
            user.setRole(Role.MEMBER);
            userRepository.save(user);

            Optional<Member> existingMember = memberRepository.findByAppUser(user);
            if (existingMember.isEmpty()) {
                Member member = new Member();
                member.setName(user.getUsername());
                member.setAppUser(user);
                memberRepository.save(member);
            }

            return "redirect:/member/dashboard";

        } else if (role.equals("TRAINER")) {
            user.setRole(Role.TRAINER);
            userRepository.save(user);

            Optional<Trainer> existingTrainer = trainerRepository.findByAppUser(user);
            if (existingTrainer.isEmpty()) {
                Trainer trainer = new Trainer();
                trainer.setName(user.getUsername());
                trainer.setAppUser(user);
                trainerRepository.save(trainer);
            }

            return "redirect:/trainer/dashboard";
        }

        return "redirect:/login?error";
    }

    @GetMapping("/choose-role")
    public String showRolePage() {
        return "choose-role";
    }
}
