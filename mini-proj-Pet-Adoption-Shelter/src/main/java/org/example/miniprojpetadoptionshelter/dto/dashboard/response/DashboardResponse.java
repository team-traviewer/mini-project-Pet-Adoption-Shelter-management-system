package org.example.miniprojpetadoptionshelter.dto.dashboard.response;

import lombok.Builder;
import lombok.Getter;
import org.example.miniprojpetadoptionshelter.entity.dashboard.Dashboard;

@Getter
@Builder
public class DashboardResponse {

    private String shelterId;

    private String fromDate;
    private String toDate;

    private Long totalCount;

    private Long protectedCount;    // 보호중
    private Long adoptedCount;      // 입양
    private Long naturalDeathCount; // 자연사
    private Long returnedCount;     // 반환
    private Long euthanasiaCount;   // 안락사
    private Long donationCount;     // 기증
    private Long releaseCount;      // 방사

    public static DashboardResponse from(Dashboard d) {
        return DashboardResponse.builder()
                .shelterId(d.getShelterId())
                .fromDate(d.getFromDate() != null ? d.getFromDate().toString() : null)
                .toDate(d.getToDate() != null ? d.getToDate().toString() : null)
                .totalCount(d.getTotalCount())
                .protectedCount(d.getProtectedCount())
                .adoptedCount(d.getAdoptedCount())
                .naturalDeathCount(d.getNaturalDeathCount())
                .returnedCount(d.getReturnedCount())
                .euthanasiaCount(d.getEuthanasiaCount())
                .donationCount(d.getDonationCount())
                .releaseCount(d.getReleaseCount())
                .build();
    }
}