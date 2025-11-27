package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;

import java.time.LocalDate;

public record FosterListRes(
        Long id,
        Long animalId,
        Long fosterUserId,
        LocalDate startDate,
        FosterStatus status
) {
}
