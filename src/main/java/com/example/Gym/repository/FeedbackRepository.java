package com.example.Gym.repository;

import com.example.Gym.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
