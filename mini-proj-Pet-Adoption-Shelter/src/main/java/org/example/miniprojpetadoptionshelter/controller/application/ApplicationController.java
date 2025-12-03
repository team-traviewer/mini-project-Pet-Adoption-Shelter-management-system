package org.example.miniprojpetadoptionshelter.controller.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.application.ApplicationApi;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCancelReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCreateReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationRejectReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationDetailRes;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.entity.application.Application;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.application.ApplicationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ApplicationController {
    private final ApplicationService applicationService;

    // 1) 입양 신청 생성
    @PreAuthorize("hasRole('USER')")
    @PostMapping(ApplicationApi.ROOT)
    public ResponseEntity<ResponseDto<Void>> createApplication(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody ApplicationCreateReq req
    ) {
        ResponseDto<Void> response = applicationService.createApplication(principal, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) 입양 신청 조회 (전체 조회)
    @PreAuthorize("hasAnyRole('USER', 'STAFF', 'ADMIN')")
    @GetMapping(ApplicationApi.ROOT)
    public ResponseEntity<ResponseDto<List<ApplicationListRes>>> getApplications(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam(required = false) Long animalId,
            @RequestParam(required = false) Long applicantId,
            @RequestParam(required = false)ApplicationStatus status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate to
    ) {
        ResponseDto<List<ApplicationListRes>> response = applicationService.getApplications(principal, animalId, applicantId, status, from, to);
        return ResponseEntity.ok().body(response);
    }

    // 3) 입양 신청 단건 조회 (id)
    @PreAuthorize("hasAnyRole('USER', 'STAFF', 'ADMIN')")
    @GetMapping(ApplicationApi.BY_ID)
    public ResponseEntity<ResponseDto<ApplicationDetailRes>> getApplicationById(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId
    ) {
        ResponseDto<ApplicationDetailRes> response = applicationService.getApplicationById(principal, applicationId);
        return ResponseEntity.ok().body(response);
    }

    // 4) 입양 신청 심사 취소 (APPLIED -> CANCELED)
    @PreAuthorize("hasAnyRole('USER', 'STAFF', 'ADMIN')")
    @PutMapping(ApplicationApi.CANCEL)
    public ResponseEntity<ResponseDto<Void>> cancelApplicationById(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId,
            @Valid @RequestBody ApplicationCancelReq req
    ) {
        ResponseDto<Void> response = applicationService.cancelApplicationById(principal, applicationId, req);
        return ResponseEntity.ok().body(response);
    }

    // 5) 입양 신청 심사 시작 (APPLIED -> REVIEW)
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping(ApplicationApi.REVIEW)
    public ResponseEntity<ResponseDto<Void>> reviewApplicationById(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId,
            @Valid @RequestBody ApplicationUpdateReq req
    ) {
        ResponseDto<Void> response = applicationService.reviewApplicationById(principal, applicationId, req);
        return ResponseEntity.ok().body(response);
    }

    // 6) 입양 신청 심사 승인(REVIEW -> APPROVED)
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping(ApplicationApi.APPROVE)
    public ResponseEntity<ResponseDto<Void>> approveApplicationById(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId
    ) {
        ResponseDto<Void> response = applicationService.approveApplicationById(principal, applicationId);
        return ResponseEntity.ok().body(response);
    }

    // 7) 입양 신청 심사 거절(REVIEW -> REJECTED)
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping(ApplicationApi.REJECT)
    public ResponseEntity<ResponseDto<Void>> rejectApplicationById(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("applicationId") @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId,
            @Valid @RequestBody ApplicationRejectReq req
    ) {
        ResponseDto<Void> response = applicationService.rejectApplicationById(principal, applicationId, req);
        return ResponseEntity.ok().body(response);
    }
}
