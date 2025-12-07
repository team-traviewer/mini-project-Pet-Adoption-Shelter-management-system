package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response;

import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record IntakeDetailRes(
        Long id,
        Long animalId,
        LocalDateTime intakeDate,
        IntakeReason intakeReason,
        String foundLocation,
        String note,
        LocalDateTime createdAt
) {
}
