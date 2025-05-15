package com.example.Gym.controller;
import com.example.Gym.model.Member;
import com.example.Gym.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @PostMapping
    public Member create(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable Long id, @RequestBody Member member) {
        member.setId(id);
        return memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memberRepository.deleteById(id);
    }
}