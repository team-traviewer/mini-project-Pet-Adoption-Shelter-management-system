package org.example.miniprojpetadoptionshelter.dto.application.response;

import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.Species;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ApplicationDetailRes(
        Long id,
        Species species,
        ApplicationStatus status,
        String message,
        String applicantName,
        LocalDateTime interviewAt,
        Boolean homeCheck,
        String reason,
        String createdAtKst,
        String updatedAtKst
) {}
