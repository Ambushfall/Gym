package com.example.Gym.controller;

import com.example.Gym.model.Member;
import com.example.Gym.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("memberForm", new Member());
        return "member";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("memberForm", memberRepository.findById(id).orElse(new Member()));
        return "member";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("memberForm") Member member) {
        memberRepository.save(member);
        return "redirect:/members";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("memberForm") Member member) {
        member.setId(id);
        memberRepository.save(member);
        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return "redirect:/members";
    }
}
