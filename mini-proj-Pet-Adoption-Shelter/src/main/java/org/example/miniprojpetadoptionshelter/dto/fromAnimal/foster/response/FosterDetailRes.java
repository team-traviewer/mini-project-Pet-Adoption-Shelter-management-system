package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;

import java.time.LocalDate;

//@JsonInclude(JsonInclude.Include.NON_NULL)
// 테스팅 위해 endDate: null 을 보고싶으면 @JsonInclude 는 달지마라
public record FosterDetailRes(
        Long id,
        Long animalId,
        String animalSpecies,
        String animalBreed,
        Long fosterUserId,
        LocalDate startDate,
        LocalDate endDate,
        FosterStatus status,
        String note,
        String createdAtKst,
        String createdUtcIso
) {
    public static FosterDetailRes from(Foster foster) {
        if (foster == null) return null;

        return new FosterDetailRes(
                foster.getId(),
                foster.getAnimal().getId(),
                foster.getAnimal().getSpecies(),
                foster.getAnimal().getBreed() != null ? foster.getAnimal().getBreed() : "unknown",
                foster.getFosterUser().getId(),
                foster.getStartDate(),
                foster.getEndDate(),
                foster.getStatus(),
                foster.getNote(),
                DateUtils.toKstString(foster.getCreatedAt()),
                DateUtils.toUtcString(foster.getCreatedAt())
        );
    }
}
