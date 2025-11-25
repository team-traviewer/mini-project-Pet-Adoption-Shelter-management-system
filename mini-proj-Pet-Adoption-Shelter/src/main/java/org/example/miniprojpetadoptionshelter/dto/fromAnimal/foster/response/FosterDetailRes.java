package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response;

import java.time.LocalDate;

public record FosterDetailRes(
        Long id,
        Long animalId,
        Long fosterUserId,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        String note,
        LocalDate createdAt
) {
}
