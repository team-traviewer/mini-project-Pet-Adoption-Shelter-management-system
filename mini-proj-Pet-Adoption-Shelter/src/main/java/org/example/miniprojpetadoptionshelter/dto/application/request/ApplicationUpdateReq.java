package org.example.miniprojpetadoptionshelter.dto.application.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationUpdateReq(
        @FutureOrPresent(message = "마감일은 오늘 이후로 설정해야 합니다.")
        LocalDateTime interviewAt,

        @NotNull
        Boolean homeCheck
) {}
