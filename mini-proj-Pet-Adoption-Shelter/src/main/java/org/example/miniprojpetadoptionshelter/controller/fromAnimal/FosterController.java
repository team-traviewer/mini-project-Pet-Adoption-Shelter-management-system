package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseDto<List<FosterListRes>>> getFosterList(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam (required = false) Long fosterUserId,
            @RequestParam (required = false) FosterStatus status,
            @RequestParam (required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam (required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        ResponseDto<List<FosterListRes>> response = fosterService.getFosterList(principal, fosterUserId, status, from, to);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(FosterApi.BY_ID)
    public ResponseEntity<ResponseDto<FosterDetailRes>> getFosterDetail(
            @PathVariable("fosterId") @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<FosterDetailRes> response = fosterService.getFosterDetail(fosterId, principal);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @PutMapping(FosterApi.CLOSE)
    public ResponseEntity<ResponseDto<Void>> closeFoster(
            @PathVariable("fosterId") @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId,
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody FosterCloseReq req
    ) {
        ResponseDto<Void> response = fosterService.closeFoster(fosterId, principal, req);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @PutMapping(FosterApi.CANCEL)
    public ResponseEntity<ResponseDto<Void>> cancelFoster(
            @PathVariable("fosterId") @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId,
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody FosterCancelReq req
    ) {
        ResponseDto<Void> response = fosterService.cancelFoster(fosterId, principal, req);
        return ResponseEntity.ok().body(response);
    }
}
