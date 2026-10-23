package com.internship.internship_tracker.controller;

import com.internship.internship_tracker.model.Internship;
import com.internship.internship_tracker.service.InternshipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public Internship getInternshipById(@PathVariable Long id) {
        return internshipService.getInternshipById(id);
    }

    @PostMapping
    public ResponseEntity<Internship> createInternship(
            @RequestParam Long organizationId,
            @Valid @RequestBody Internship internship) {
        Internship created = internshipService.createInternship(organizationId, internship);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public Internship updateInternship(@PathVariable Long id, @Valid @RequestBody Internship internship) {
        return internshipService.updateInternship(id, internship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.noContent().build();
    }
}
