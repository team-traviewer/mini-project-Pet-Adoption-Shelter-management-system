package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request;

import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;

import java.time.LocalDateTime;

public record IntakeCreateReq(
        LocalDateTime intakeDate,
        IntakeReason intakeReason,   // STRAY/SURRENDER/TRANSFER
        String foundLocation,
        String note
) {
}
