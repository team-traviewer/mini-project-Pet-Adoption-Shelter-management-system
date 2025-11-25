package org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicalUpdateReq(
        LocalDate recordDate,
        String type,
        String description,
        BigDecimal cost
) {
}
