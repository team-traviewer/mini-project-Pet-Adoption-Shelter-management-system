package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.fromAnimal.FosterApi;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCancelReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCloseReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;
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
public class FosterController {
    private final FosterService fosterService;

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping(FosterApi.ROOT)
    public ResponseEntity<ResponseDto<Void>> createFoster(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody FosterCreateReq req
    ) {
        ResponseDto<Void> response = fosterService.createFoster(principal, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(FosterApi.ROOT)
    public ResponseEntity<ResponseDto<FosterListRes.PageResponse>> getFosterList(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(required = false) String[] sort,
            @RequestParam (required = false) Long fosterUserId,
            @RequestParam (required = false) FosterStatus status,
            @RequestParam (required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam (required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate

    ) {
        ResponseDto<FosterListRes.PageResponse> response = fosterService.getFosterList(principal, page, size, sort,  fosterUserId, status, startDate, endDate);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(FosterApi.BY_ID)
    public ResponseEntity<ResponseDto<FosterDetailRes>> getFosterDetail(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("fosterId") @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId
    ) {
        ResponseDto<FosterDetailRes> response = fosterService.getFosterDetail(principal, fosterId);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @PutMapping(FosterApi.CLOSE)
    public ResponseEntity<ResponseDto<Void>> closeFoster(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("fosterId") @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId,
            @Valid @RequestBody FosterCloseReq req
    ) {
        ResponseDto<Void> response = fosterService.closeFoster(principal,fosterId, req);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @PutMapping(FosterApi.CANCEL)
    public ResponseEntity<ResponseDto<Void>> cancelFoster(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("fosterId") @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId,
            @Valid @RequestBody FosterCancelReq req
    ) {
        ResponseDto<Void> response = fosterService.cancelFoster(principal, fosterId, req);
        return ResponseEntity.ok().body(response);
    }
}
