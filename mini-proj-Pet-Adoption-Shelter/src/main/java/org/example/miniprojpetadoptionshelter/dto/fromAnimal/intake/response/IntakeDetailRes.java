package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response;

import java.time.LocalDate;

public record IntakeDetailRes(
        Long id,
        Long animalId,
        LocalDate intakeDate,
        String intakeReason,
        String foundLocation,
        String note,
        LocalDate createdAt
) {
}
