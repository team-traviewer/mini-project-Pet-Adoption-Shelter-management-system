package org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FosterCreateReq(
        @NotNull
        Long animalId,

        @NotNull
        Long fosterUserId,

        @NotNull(message = "임시보호 시작일을 작성해주세요.")
        @FutureOrPresent(message = "임시보호 시작일은 오늘 이후로 설정해야 합니다.")
        LocalDate startDate,

        String note
) {}
