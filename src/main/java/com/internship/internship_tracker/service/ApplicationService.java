package com.internship.internship_tracker.service;

import com.internship.internship_tracker.exception.ResourceNotFoundException;
import com.internship.internship_tracker.model.Application;
import com.internship.internship_tracker.model.ApplicationStatus;
import com.internship.internship_tracker.model.Internship;
import com.internship.internship_tracker.model.Student;
import com.internship.internship_tracker.repository.ApplicationRepository;
import com.internship.internship_tracker.repository.InternshipRepository;
import com.internship.internship_tracker.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              InternshipRepository internshipRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
    }

    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<Application> getApplicationsByInternship(Long internshipId) {
        return applicationRepository.findByInternshipId(internshipId);
    }

    public Application applyForInternship(Long studentId, Long internshipId, String coverLetter) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found with id: " + internshipId));

        Application application = new Application();
        application.setStudent(student);
        application.setInternship(internship);
        application.setCoverLetter(coverLetter);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedDate(LocalDate.now());
        return applicationRepository.save(application);
    }

    public Application updateApplicationStatus(Long id, ApplicationStatus status) {
        Application application = getApplicationById(id);
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        Application application = getApplicationById(id);
        applicationRepository.delete(application);
    }
}
