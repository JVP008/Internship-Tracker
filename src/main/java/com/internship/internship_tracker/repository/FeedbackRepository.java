package com.internship.internship_tracker.repository;

import com.internship.internship_tracker.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByApplicationId(Long applicationId);
}
