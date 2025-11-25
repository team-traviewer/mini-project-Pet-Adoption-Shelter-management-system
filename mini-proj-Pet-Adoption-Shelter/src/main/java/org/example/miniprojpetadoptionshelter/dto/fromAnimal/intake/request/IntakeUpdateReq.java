package org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request;

import java.time.LocalDate;

public record IntakeUpdateReq(
        LocalDate intakeDate,
        String intakeReason,
        String foundLocation,
        String note
){
}
