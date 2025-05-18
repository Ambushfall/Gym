package com.example.Gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class DashboardController {

    @GetMapping("/{role}/dashboard")
    public String dashboard(@PathVariable String role, Principal principal, Model model) {
        model.addAttribute("role", role.toUpperCase());
        model.addAttribute("username", principal.getName());
        return "shared-dashboard"; // Thymeleaf šablon
    }
}

//    @GetMapping("/member/dashboard")
//    public String memberDashboard() {
//        return "member-dashboard";
//    }
//
//    @GetMapping("/trainer/dashboard")
//    public String trainerDashboard() {
//        return "trainer-dashboard";
//    }
//
//    @GetMapping("/admin/dashboard")
//    public String adminDashboard() {
//        return "admin-dashboard";
//    }

