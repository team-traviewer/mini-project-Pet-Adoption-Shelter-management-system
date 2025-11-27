package org.example.miniprojpetadoptionshelter.service.dashboard;

import org.example.miniprojpetadoptionshelter.dto.dashboard.response.DashboardResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DashboardService {
    DashboardResponse getDashboard(Long shelterId, LocalDate from, LocalDate to);
}
