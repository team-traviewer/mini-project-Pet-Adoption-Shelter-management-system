package org.example.miniprojpetadoptionshelter.service.dashboard.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.dashboard.response.DashboardResponse;
import org.example.miniprojpetadoptionshelter.entity.dashboard.Dashboard;
import org.example.miniprojpetadoptionshelter.repository.dashboard.DashboardRepository;
import org.example.miniprojpetadoptionshelter.service.dashboard.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    /**
     * 보호소 기본 대시보드 조회
     */
    @Override
    public DashboardResponse getDashboard(String shelterId) {
        Dashboard dashboard = dashboardRepository
                .findByShelterId(shelterId)
                .orElseThrow(() -> new RuntimeException("대시보드 데이터가 없습니다."));

        return DashboardResponse.from(dashboard);
    }

    /**
     * 보호소 + 기간 검색 대시보드 조회
     */
    @Override
    public DashboardResponse getDashboard(String shelterId, LocalDate from, LocalDate to) {
        Dashboard dashboard = dashboardRepository
                .findDashboard(shelterId, from, to)
                .orElseThrow(() -> new RuntimeException("해당 기간의 대시보드 데이터가 없습니다."));

        return DashboardResponse.from(dashboard);
    }
}