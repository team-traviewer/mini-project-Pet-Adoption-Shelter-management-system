package org.example.miniprojpetadoptionshelter.dto.application.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCreateReq(
        @NotNull
        Long animalId,
        String message
) {}
