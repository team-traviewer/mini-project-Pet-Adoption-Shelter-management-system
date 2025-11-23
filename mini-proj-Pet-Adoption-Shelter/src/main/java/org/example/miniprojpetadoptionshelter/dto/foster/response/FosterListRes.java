package org.example.miniprojpetadoptionshelter.dto.foster.response;

import java.time.LocalDate;

public record FosterListRes(
        Long id,
        Long animalId,
        Long fosterUserId,
        LocalDate startDate,
        String status
) {
}
