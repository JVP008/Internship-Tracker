package com.internship.internship_tracker.service;

import com.internship.internship_tracker.exception.ResourceNotFoundException;
import com.internship.internship_tracker.model.Application;
import com.internship.internship_tracker.model.Feedback;
import com.internship.internship_tracker.repository.ApplicationRepository;
import com.internship.internship_tracker.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ApplicationRepository applicationRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, ApplicationRepository applicationRepository) {
        this.feedbackRepository = feedbackRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));
    }

    public List<Feedback> getFeedbacksByApplication(Long applicationId) {
        return feedbackRepository.findByApplicationId(applicationId);
    }

    public Feedback createFeedback(Long applicationId, Feedback feedback) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + applicationId));
        feedback.setApplication(application);
        feedback.setFeedbackDate(LocalDate.now());
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }
}
