package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request;

import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;

import java.time.LocalDate;

public record IntakeCreateReq(
        Long animalId,
        LocalDate intakeDate,
        IntakeReason intakeReason,   // STRAY/SURRENDER/TRANSFER
        String foundLocation,
        String note
) {
}
