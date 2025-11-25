package org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.response;

import java.time.LocalDate;

public record MedicalListRes(
        Long id,
        Long animalId,
        LocalDate recordDate,
        String type
) {
}
