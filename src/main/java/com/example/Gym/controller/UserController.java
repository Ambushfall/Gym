package com.example.Gym.controller;

import com.example.Gym.model.AppUser;
import com.example.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userForm", new AppUser());
        return "user";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userForm", userRepository.findById(id).orElse(new AppUser()));
        return "user";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("userForm") AppUser user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("userForm") AppUser user) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
