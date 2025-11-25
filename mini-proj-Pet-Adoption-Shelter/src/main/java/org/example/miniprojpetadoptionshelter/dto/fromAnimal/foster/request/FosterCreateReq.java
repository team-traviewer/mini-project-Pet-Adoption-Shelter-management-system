package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request;

import java.time.LocalDate;

public record FosterCreateReq(
        Long animalId,
        Long fosterUserId,
        LocalDate startDate,
        LocalDate endDate,
        String status,   // ACTIVE/CLOSED/CANCELED
        String note
) {}
