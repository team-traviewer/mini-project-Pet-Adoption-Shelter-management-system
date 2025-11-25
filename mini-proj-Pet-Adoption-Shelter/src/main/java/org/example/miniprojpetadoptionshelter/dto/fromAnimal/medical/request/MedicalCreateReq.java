package org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicalCreateReq(
        Long animalId,
        LocalDate recordDate,
        String type,       // VACCINE/NEUTER/TREATMENT/EXAM
        String description,
        BigDecimal cost
) {
}
