package org.example.miniprojpetadoptionshelter.dto.application.response;

import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.common.enums.Species;
import org.example.miniprojpetadoptionshelter.common.utils.DateUtils;
import org.example.miniprojpetadoptionshelter.entity.application.Application;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ApplicationDetailRes(
        Long id,
        String animalSpecies,
        ApplicationStatus status,
        String message,
        String applicantName,
        LocalDateTime interviewAt,
        boolean homeCheck,
        String reason,
        String createdAtKst,
        String updatedAtKst
) {
    public static ApplicationDetailRes from(Application application) {
        if (application == null) return null;

        return new ApplicationDetailRes(
                application.getId(),
                application.getAnimal().getSpecies(),
                application.getStatus(),
                application.getMessage(),
                application.getApplicant().getName(),
                application.getInterviewAt(),
                application.isHomeCheck(),
                application.getReason(),
                DateUtils.toKstString(application.getCreatedAt()),
                DateUtils.toKstString(application.getUpdatedAt())
        );
    }
}
