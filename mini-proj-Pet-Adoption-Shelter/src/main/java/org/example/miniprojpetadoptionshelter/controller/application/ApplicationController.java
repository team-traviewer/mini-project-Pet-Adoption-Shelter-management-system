package org.example.miniprojpetadoptionshelter.controller.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.application.ApplicationApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCancelReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCreateReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationRejectReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationDetailRes;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.application.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    // 1) 입양 신청 생성
    @PreAuthorize("hasAnyRole('USER', 'STAFF', 'ADMIN')")
    @PostMapping(ApplicationApi.ROOT)
    public ResponseEntity<ResponseDto<Void>> createApplication(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody ApplicationCreateReq req
    ) {
        ResponseDto<Void> response = applicationService.createApplication(principal, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) 입양 신청 조회 (전체 조회 - 내림차순/오름차순)
    @PreAuthorize("hasRole('STAFF')")
    @GetMapping(ApplicationApi.ROOT)
    public ResponseEntity<ResponseDto<List<ApplicationListRes>>> getAllApplicationsOrderByCreatedAt(
            @AuthenticationPrincipal User principal,
            @RequestParam("sortedBy") boolean sortedBy
    ) {
        ResponseDto<List<ApplicationListRes>> response = applicationService.getAllApplicationsOrderByCreatedAt(principal, sortedBy);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 3) 입양 신청 단건 조회 (id)
    @PreAuthorize("hasRole('STAFF')")
    @GetMapping(ApplicationApi.BY_ID)
    public ResponseEntity<ResponseDto<ApplicationDetailRes>> getApplicationById(
            @AuthenticationPrincipal User principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId

    ) {
        ResponseDto<ApplicationDetailRes> response = applicationService.getApplicationById(principal, applicationId);
        return ResponseEntity.ok().body(response);
    }

    // 4) 입양 신청 심사 취소 (APPLIED -> CANCELED)
    @PreAuthorize("hasAnyRole('USER', 'STAFF', 'ADMIN')")
    @PutMapping(ApplicationApi.CANCEL)
    public ResponseEntity<ResponseDto<Void>> changeApplicationStatusToCanceledById(
            @AuthenticationPrincipal User principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId,
            @Valid @RequestBody ApplicationCancelReq req
    ) {
        ResponseDto<Void> response = applicationService.changeApplicationStatusToCanceledById(principal, applicationId, req);
        return ResponseEntity.ok().body(response);
    }

    // 5) 입양 신청 심사 시작 (APPLIED -> REVIEW)
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping(ApplicationApi.REVIEW)
    public ResponseEntity<ResponseDto<Void>> changeApplicationStatusToReviewById(
            @AuthenticationPrincipal User principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId,
            @Valid @RequestBody ApplicationUpdateReq req
    ) {
        ResponseDto<Void> response = applicationService.startApplicationReviewById(principal, applicationId, req);
        return ResponseEntity.ok().body(response);
    }

    // 6) 입양 신청 심사 승인(REVIEW -> APPROVED)
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping(ApplicationApi.APPROVE)
    public ResponseEntity<ResponseDto<Void>> changeApplicationStatusToApprovedById(
            @AuthenticationPrincipal User principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId
    ) {
        ResponseDto<Void> response = applicationService.changeApplicationStatusToApprovedById(principal, applicationId);
        return ResponseEntity.ok().body(response);
    }

    // 7) 입양 신청 심사 거절(REVIEW -> REJECTED)
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping(ApplicationApi.REJECT)
    public ResponseEntity<ResponseDto<Void>> changeApplicationStatusToRejectedById(
            @AuthenticationPrincipal User principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId,
            @Valid @RequestBody ApplicationRejectReq req
    ) {
        ResponseDto<Void> response = applicationService.changeApplicationStatusToRejectedById(principal, applicationId, req);
        return ResponseEntity.ok().body(response);
    }
}
