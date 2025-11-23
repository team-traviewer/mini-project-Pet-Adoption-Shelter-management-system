package org.example.miniprojpetadoptionshelter.dto.medical.response;

import java.time.LocalDate;

public record MedicalListRes(
        Long id,
        Long animalId,
        LocalDate recordDate,
        String type
) {
}
