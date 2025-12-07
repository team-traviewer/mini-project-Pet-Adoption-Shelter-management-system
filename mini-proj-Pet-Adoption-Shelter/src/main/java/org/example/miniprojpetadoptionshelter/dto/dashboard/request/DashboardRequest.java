package org.example.miniprojpetadoptionshelter.dto.dashboard.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DashboardRequest {

    private String shelterId;
    private LocalDate fromDate;
    private LocalDate toDate;
}