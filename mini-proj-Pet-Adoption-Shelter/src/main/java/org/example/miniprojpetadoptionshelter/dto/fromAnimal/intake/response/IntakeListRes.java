package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response;

import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;

import java.time.LocalDate;

public record IntakeListRes(
        Long id,
        Long animalId,
        LocalDate intakeDate,
        IntakeReason intakeReason
) {
}
