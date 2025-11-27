package org.example.miniprojpetadoptionshelter.service.dashboard.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.dashboard.response.DashboardResponse;
import org.example.miniprojpetadoptionshelter.service.dashboard.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    @Override
    public DashboardResponse getDashboard(Long shelterId, LocalDate from, LocalDate to) {
        return null;
    }
}
