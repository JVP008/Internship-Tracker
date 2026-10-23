package com.internship.internship_tracker.controller;

import com.internship.internship_tracker.dto.ApplicationRequest;
import com.internship.internship_tracker.dto.StatusUpdateRequest;
import com.internship.internship_tracker.model.Application;
import com.internship.internship_tracker.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getApplicationsByStudent(@PathVariable Long studentId) {
        return applicationService.getApplicationsByStudent(studentId);
    }

    @GetMapping("/internship/{internshipId}")
    public List<Application> getApplicationsByInternship(@PathVariable Long internshipId) {
        return applicationService.getApplicationsByInternship(internshipId);
    }

    @PostMapping
    public ResponseEntity<Application> applyForInternship(@Valid @RequestBody ApplicationRequest request) {
        Application application = applicationService.applyForInternship(
                request.getStudentId(),
                request.getInternshipId(),
                request.getCoverLetter()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @PatchMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest request) {
        return applicationService.updateApplicationStatus(id, request.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
