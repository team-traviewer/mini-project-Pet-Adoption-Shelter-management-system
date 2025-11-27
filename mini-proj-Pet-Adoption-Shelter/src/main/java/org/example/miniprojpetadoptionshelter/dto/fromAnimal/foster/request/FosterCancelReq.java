package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FosterCancelReq(
        String note
) {}
