package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;

import java.time.LocalDate;

public record FosterCreateReq(
        Long animalId,
        Long fosterUserId,
        LocalDate startDate,
        LocalDate endDate,
        FosterStatus status,   // ACTIVE/CLOSED/CANCELED
        String note
) {}
