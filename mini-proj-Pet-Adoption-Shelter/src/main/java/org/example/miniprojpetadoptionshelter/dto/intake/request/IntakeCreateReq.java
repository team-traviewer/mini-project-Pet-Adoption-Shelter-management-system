package org.example.miniprojpetadoptionshelter.dto.intake.request;

import java.time.LocalDate;

public record IntakeCreateReq(
        Long animalId,
        LocalDate intakeDate,
        String intakeReason,   // STRAY/SURRENDER/TRANSFER
        String foundLocation,
        String note
) {
}
