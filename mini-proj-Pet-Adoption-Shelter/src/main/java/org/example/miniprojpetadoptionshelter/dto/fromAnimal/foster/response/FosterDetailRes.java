package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;

import java.time.LocalDate;

public record FosterDetailRes(
        Long id,
        Long animalId,
        Long fosterUserId,
        LocalDate startDate,
        LocalDate endDate,
        FosterStatus status,
        String note,
        LocalDate createdAt
) {
}
