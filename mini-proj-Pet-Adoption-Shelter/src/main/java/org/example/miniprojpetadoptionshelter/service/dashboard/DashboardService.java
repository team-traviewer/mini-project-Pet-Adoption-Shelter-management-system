package org.example.miniprojpetadoptionshelter.service.dashboard;

import org.example.miniprojpetadoptionshelter.dto.dashboard.response.DashboardResponse;

import java.time.LocalDate;

public interface DashboardService {

    // 보호소 기본 대시보드 조회
    DashboardResponse getDashboard(String shelterId);

    // 기간 조회 포함
    DashboardResponse getDashboard(String shelterId, LocalDate from, LocalDate to);
}