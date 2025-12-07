package org.example.miniprojpetadoptionshelter.controller.dashboard;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.dashboard.DashboardApi;
import org.example.miniprojpetadoptionshelter.dto.dashboard.response.DashboardResponse;
import org.example.miniprojpetadoptionshelter.service.dashboard.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(DashboardApi.ROOT)
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * 보호소 대시보드 조회 (기간 선택 가능)
     * GET /shelters/{shelterId}/dashboard?from=2024-01-01&to=2024-01-31
     */
    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(
            @PathVariable String shelterId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to
    ) {

        DashboardResponse response;

        // 기간이 없는 경우 → 기본 대시보드 조회
        if (from == null || to == null) {
            response = dashboardService.getDashboard(shelterId);
        }
        // 기간이 있는 경우 → 기간 기반 조회
        else {
            response = dashboardService.getDashboard(shelterId, from, to);
        }

        return ResponseEntity.ok(response);
    }
}