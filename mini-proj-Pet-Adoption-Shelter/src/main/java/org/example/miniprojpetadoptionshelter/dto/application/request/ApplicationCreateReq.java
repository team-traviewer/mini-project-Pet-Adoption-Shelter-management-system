package org.example.miniprojpetadoptionshelter.dto.application.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCreateReq(
        String message
) {}
