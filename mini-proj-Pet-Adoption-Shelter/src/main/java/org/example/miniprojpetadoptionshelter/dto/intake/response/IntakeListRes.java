package org.example.miniprojpetadoptionshelter.dto.intake.response;

import java.time.LocalDate;

public record IntakeListRes(
        Long id,
        Long animalId,
        LocalDate intakeDate,
        String intakeReason
) {
}
