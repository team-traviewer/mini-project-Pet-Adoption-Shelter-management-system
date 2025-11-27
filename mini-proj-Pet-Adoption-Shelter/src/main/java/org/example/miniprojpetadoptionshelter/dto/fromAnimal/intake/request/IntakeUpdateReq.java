package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request;

import org.example.miniprojpetadoptionshelter.common.enums.IntakeReason;

import java.time.LocalDate;

public record IntakeUpdateReq(
        LocalDate intakeDate,
        IntakeReason intakeReason,
        String foundLocation,
        String note
){
}
