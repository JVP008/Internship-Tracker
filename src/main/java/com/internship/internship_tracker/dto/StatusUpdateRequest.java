package com.internship.internship_tracker.dto;

import com.internship.internship_tracker.model.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateRequest {

    @NotNull(message = "Status is required")
    private ApplicationStatus status;
}
