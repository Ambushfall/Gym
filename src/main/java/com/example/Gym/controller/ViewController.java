package com.example.Gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

//    @GetMapping("/login")
//    public String loginPage() {
//        return "login.html"; // Thymeleaf će prikazati login.html
//    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
