package org.example.miniprojpetadoptionshelter.repository.fromAnimal;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Foster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FosterRepositoryCustom {
    Page<Foster> searchFosters(Long fosterUserId, FosterStatus status, LocalDate startDate, LocalDate endDate, Pageable pageable);
}