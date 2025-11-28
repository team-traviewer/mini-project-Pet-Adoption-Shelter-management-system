package org.example.miniprojpetadoptionshelter.dto.application.response;

import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.Species;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.entity.application.Application;

public record ApplicationListRes(
        Long id,
        String animalSpecies,
        ApplicationStatus status,
        String message,
        String applicantName,
        String createdAtKst
) {
    public static ApplicationListRes from(Application application) {
        if (application == null) return null;

        return new ApplicationListRes(
                application.getId(),
                application.getAnimal().getSpecies(),
                application.getStatus(),
                application.getMessage(),
                application.getUser().getName(),
                DateUtils.toKstString(application.getCreatedAt())
        );
    }
}
