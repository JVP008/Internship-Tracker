package com.internship.internship_tracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Internship ID is required")
    private Long internshipId;

    private String coverLetter;
}
