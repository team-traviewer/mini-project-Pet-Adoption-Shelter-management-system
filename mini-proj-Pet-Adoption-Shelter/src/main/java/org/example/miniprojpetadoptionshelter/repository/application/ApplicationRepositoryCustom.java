package org.example.miniprojpetadoptionshelter.repository.application;

import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ApplicationRepositoryCustom {
    Page<Application> searchApplications(Long animalId, Long searchApplicantId, ApplicationStatus status, LocalDateTime fromUtc, LocalDateTime toUtc, Pageable pageable);
}
