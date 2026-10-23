package com.internship.internship_tracker.service;

import com.internship.internship_tracker.exception.ResourceNotFoundException;
import com.internship.internship_tracker.model.Internship;
import com.internship.internship_tracker.model.Organization;
import com.internship.internship_tracker.repository.InternshipRepository;
import com.internship.internship_tracker.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final OrganizationRepository organizationRepository;

    public InternshipService(InternshipRepository internshipRepository, OrganizationRepository organizationRepository) {
        this.internshipRepository = internshipRepository;
        this.organizationRepository = organizationRepository;
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found with id: " + id));
    }

    public Internship createInternship(Long organizationId, Internship internship) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + organizationId));
        internship.setOrganization(organization);
        return internshipRepository.save(internship);
    }

    public Internship updateInternship(Long id, Internship updatedInternship) {
        Internship internship = getInternshipById(id);
        internship.setTitle(updatedInternship.getTitle());
        internship.setDescription(updatedInternship.getDescription());
        internship.setLocation(updatedInternship.getLocation());
        internship.setStipend(updatedInternship.getStipend());
        internship.setDurationInWeeks(updatedInternship.getDurationInWeeks());
        return internshipRepository.save(internship);
    }

    public void deleteInternship(Long id) {
        Internship internship = getInternshipById(id);
        internshipRepository.delete(internship);
    }
}
