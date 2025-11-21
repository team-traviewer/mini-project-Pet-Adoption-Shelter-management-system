package org.example.miniprojpetadoptionshelter.dto.application.response;

import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.Species;

import java.time.LocalDate;

public record ApplicationDetailRes(
        Long id,
        Species species,
        ApplicationStatus status,
        String message,
        String applicantName,
        LocalDate interviewAt,
        Boolean homeCheck,
        String reason,
        String createdAtKst,
        String updatedAtKst
) {
}
