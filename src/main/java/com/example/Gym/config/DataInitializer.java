package com.example.Gym.config;

import com.example.Gym.model.*;
import com.example.Gym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private TrainerRepository trainerRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private TrainingSessionRepository trainingSessionRepository;
    @Autowired private FeedbackRepository feedbackRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return; // ne radi ništa ako već postoji nešto

        // 1. User-i
        for (int i = 1; i <= 5; i++) {
            AppUser user = new AppUser();
            user.setUsername("user" + i);
            user.setPassword(passwordEncoder.encode("password" + i));
            user.setRole(Role.USER); // svi kao USER, mogu kasnije da izaberu MEMBER/TRENER
            userRepository.save(user);
        }

        // 2. Treneri
        for (int i = 1; i <= 5; i++) {
            AppUser trainerUser = new AppUser();
            trainerUser.setUsername("trainer" + i);
            trainerUser.setPassword(passwordEncoder.encode("trainer" + i));
            trainerUser.setRole(Role.TRAINER);
            userRepository.save(trainerUser);

            Trainer trainer = new Trainer();
            trainer.setName("Trener " + i);
            trainer.setSpecialization("Specijalizacija " + i);
            trainer.setAppUser(trainerUser);
            trainerRepository.save(trainer);
        }

        // 3. Članovi
        for (int i = 1; i <= 5; i++) {
            AppUser memberUser = new AppUser();
            memberUser.setUsername("member" + i);
            memberUser.setPassword(passwordEncoder.encode("member" + i));
            memberUser.setRole(Role.MEMBER);
            userRepository.save(memberUser);

            Member member = new Member();
            member.setName("Član " + i);
            member.setEmail("member" + i + "@gym.com");
            member.setPhoneNumber("06012345" + i);
            member.setMembershipType("Standard");
            member.setAppUser(memberUser);
            memberRepository.save(member);
        }

        // 4. Trening sesije
        List<Trainer> trainers = trainerRepository.findAll();
        for (int i = 1; i <= 5; i++) {
            TrainingSession session = new TrainingSession();
            session.setTitle("Trening " + i);
            session.setDescription("Opis treninga " + i);
            session.setTrainer(trainers.get(i % trainers.size()));
            trainingSessionRepository.save(session);
        }

        // 5. Feedbackovi
        List<Member> members = memberRepository.findAll();
        for (int i = 1; i <= 5; i++) {
            Feedback feedback = new Feedback();
            feedback.setContent("Komentar " + i);
            feedback.setRating(i % 5 + 1);
            feedback.setMember(members.get(i % members.size()));
            feedbackRepository.save(feedback);
        }

        System.out.println("✅ Demo podaci uspešno ubačeni.");
    }
}
