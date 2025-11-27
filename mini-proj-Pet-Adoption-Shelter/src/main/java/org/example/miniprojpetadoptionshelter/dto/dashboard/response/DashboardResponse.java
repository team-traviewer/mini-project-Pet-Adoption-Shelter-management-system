package org.example.miniprojpetadoptionshelter.dto.dashboard.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DashboardResponse {

    private PeriodDto period;

    private int newIntakes;
    private int adoptableAnimals;
    private int inTreatment;
    private int inTemporaryCare;

    private AdoptionStats adoption;

    @Getter
    @Builder
    public static class PeriodDto {
        private LocalDate from;
        private LocalDate to;
    }

    @Getter
    @Builder
    public static class AdoptionStats {
        private int applications;
        private int approved;
        private int rejected;
        private int pending;
    }
}