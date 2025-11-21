package org.example.miniprojpetadoptionshelter.dto.application.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ApplicationUpdateReq(

        @FutureOrPresent(message = "마감일은 오늘 이후로 설정해야 합니다.")
        LocalDate interviewAt,

        @NotNull
        Boolean homeCheck
) {
}
