package com.internship.internship_tracker.service;

import com.internship.internship_tracker.exception.ResourceNotFoundException;
import com.internship.internship_tracker.model.Organization;
import com.internship.internship_tracker.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
    }

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(Long id, Organization updatedOrg) {
        Organization org = getOrganizationById(id);
        org.setName(updatedOrg.getName());
        org.setIndustry(updatedOrg.getIndustry());
        org.setWebsite(updatedOrg.getWebsite());
        org.setLocation(updatedOrg.getLocation());
        return organizationRepository.save(org);
    }

    public void deleteOrganization(Long id) {
        Organization org = getOrganizationById(id);
        organizationRepository.delete(org);
    }
}
