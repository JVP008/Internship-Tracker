package com.internship.internship_tracker.controller;

import com.internship.internship_tracker.model.Application;
import com.internship.internship_tracker.model.ApplicationStatus;
import com.internship.internship_tracker.model.Feedback;
import com.internship.internship_tracker.model.Internship;
import com.internship.internship_tracker.model.Organization;
import com.internship.internship_tracker.model.Student;
import com.internship.internship_tracker.service.ApplicationService;
import com.internship.internship_tracker.service.FeedbackService;
import com.internship.internship_tracker.service.InternshipService;
import com.internship.internship_tracker.service.OrganizationService;
import com.internship.internship_tracker.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final StudentService studentService;
    private final OrganizationService organizationService;
    private final InternshipService internshipService;
    private final ApplicationService applicationService;
    private final FeedbackService feedbackService;

    public WebController(StudentService studentService,
                         OrganizationService organizationService,
                         InternshipService internshipService,
                         ApplicationService applicationService,
                         FeedbackService feedbackService) {
        this.studentService = studentService;
        this.organizationService = organizationService;
        this.internshipService = internshipService;
        this.applicationService = applicationService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("totalOrganizations", organizationService.getAllOrganizations().size());
        model.addAttribute("totalInternships", internshipService.getAllInternships().size());
        model.addAttribute("totalApplications", applicationService.getAllApplications().size());
        model.addAttribute("recentApplications", applicationService.getAllApplications());
        return "dashboard";
    }

    // --- Students ---
    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping("/students")
    public String createStudent(@ModelAttribute Student student) {
        studentService.createStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    // --- Organizations ---
    @GetMapping("/organizations")
    public String organizations(Model model) {
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("organization", new Organization());
        return "organizations";
    }

    @PostMapping("/organizations")
    public String createOrganization(@ModelAttribute Organization organization) {
        organizationService.createOrganization(organization);
        return "redirect:/organizations";
    }

    @PostMapping("/organizations/{id}/delete")
    public String deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return "redirect:/organizations";
    }

    // --- Internships ---
    @GetMapping("/internships")
    public String internships(Model model) {
        model.addAttribute("internships", internshipService.getAllInternships());
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("internship", new Internship());
        return "internships";
    }

    @PostMapping("/internships")
    public String createInternship(@RequestParam Long organizationId, @ModelAttribute Internship internship) {
        internshipService.createInternship(organizationId, internship);
        return "redirect:/internships";
    }

    @PostMapping("/internships/{id}/delete")
    public String deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return "redirect:/internships";
    }

    // --- Applications ---
    @GetMapping("/applications")
    public String applications(Model model) {
        model.addAttribute("applications", applicationService.getAllApplications());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("internships", internshipService.getAllInternships());
        model.addAttribute("statuses", ApplicationStatus.values());
        model.addAttribute("feedback", new Feedback());
        return "applications";
    }

    @PostMapping("/applications")
    public String apply(@RequestParam Long studentId,
                        @RequestParam Long internshipId,
                        @RequestParam(required = false) String coverLetter) {
        applicationService.applyForInternship(studentId, internshipId, coverLetter);
        return "redirect:/applications";
    }

    @PostMapping("/applications/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
        applicationService.updateApplicationStatus(id, status);
        return "redirect:/applications";
    }

    @PostMapping("/applications/{id}/feedback")
    public String addFeedback(@PathVariable Long id, @ModelAttribute Feedback feedback) {
        feedbackService.createFeedback(id, feedback);
        return "redirect:/applications";
    }

    @PostMapping("/applications/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return "redirect:/applications";
    }
}
