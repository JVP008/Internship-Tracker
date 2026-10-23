package com.internship.internship_tracker.controller;

import com.internship.internship_tracker.model.Feedback;
import com.internship.internship_tracker.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/application/{applicationId}")
    public List<Feedback> getFeedbacksByApplication(@PathVariable Long applicationId) {
        return feedbackService.getFeedbacksByApplication(applicationId);
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(
            @RequestParam Long applicationId,
            @Valid @RequestBody Feedback feedback) {
        Feedback created = feedbackService.createFeedback(applicationId, feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
