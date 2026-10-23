package com.internship.internship_tracker.repository;

import com.internship.internship_tracker.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
}
