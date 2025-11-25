package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request;

import java.time.LocalDate;

public record FosterUpdateReq(
        LocalDate startDate,
        LocalDate endDate,
        String status,
        String note
) {}
