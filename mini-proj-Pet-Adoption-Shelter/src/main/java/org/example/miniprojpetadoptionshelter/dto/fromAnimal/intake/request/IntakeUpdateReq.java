package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request;

import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record IntakeUpdateReq(
        LocalDateTime intakeDate,
        IntakeReason intakeReason,
        String foundLocation,
        String note
){
}
