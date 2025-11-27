package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;

import java.time.LocalDate;

public record FosterUpdateReq(
        LocalDate startDate,
        LocalDate endDate,
        FosterStatus status,
        String note
) {}
