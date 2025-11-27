package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;

import java.time.LocalDate;

public record FosterListRes(
        Long id,
        String animalSpecies,
        LocalDate startDate,
        FosterStatus status,
        String createdAtKst,
        String createdUtcIso
) {
    public static FosterListRes from(Foster foster) {
        if (foster == null) return null;

        return new FosterListRes(
                foster.getId(),
                foster.getAnimal().getSpecies(),
                foster.getStartDate(),
                foster.getStatus(),
                DateUtils.toKstString(foster.getCreatedAt()),
                DateUtils.toUtcString(foster.getCreatedAt())
        );
    }
}
