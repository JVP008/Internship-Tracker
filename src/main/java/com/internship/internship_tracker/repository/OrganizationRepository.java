package com.internship.internship_tracker.repository;

import com.internship.internship_tracker.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
