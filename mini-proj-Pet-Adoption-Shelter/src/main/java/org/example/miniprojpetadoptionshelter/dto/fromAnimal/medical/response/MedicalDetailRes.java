package org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicalDetailRes(
        Long id,
        Long animalId,
        LocalDate recordDate,
        String type,
        String description,
        BigDecimal cost,
        LocalDateTime createdAt
) {
}
