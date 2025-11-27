package org.example.miniprojpetadoptionshelter.dto.animal.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record  AnimalDetailRes(
        Long animalId,
        Long shelterId,
        String name,
        String species,
        String breed,
        String sex,
        BigDecimal ageYears,
        BigDecimal weightKg,
        String temperament,
        String status,
        List<AnimalFileDto> files,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
