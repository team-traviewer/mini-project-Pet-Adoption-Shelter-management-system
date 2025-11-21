package org.example.miniprojpetadoptionshelter.dto.application.response;

import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.Species;

public record ApplicationListRes(
        Long id,
        Species species,
        ApplicationStatus status,
        String message,
        String applicantName,
        String createdAtKst
) {
}
