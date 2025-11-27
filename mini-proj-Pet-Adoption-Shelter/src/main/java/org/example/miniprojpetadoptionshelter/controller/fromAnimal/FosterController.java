package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.miniprojpetadoptionshelter.common.apis.fromAnimal.FosterApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FosterController {
    private final FosterService fosterService;

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping(FosterApi.ROOT)
    public ResponseEntity<ResponseDto<Void>> createFoster(
            @RequestBody FosterCreateReq req,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<Void> response = fosterService.createFoster(principal, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(FosterApi.ROOT)
    public ResponseEntity<ResponseDto<List<FosterListRes>>> getFosterList(
            @RequestParam Long animalId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<List<FosterListRes>> response = fosterService.getFosterList(animalId, principal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(FosterApi.BY_ID)
    public ResponseEntity<ResponseDto<FosterDetailRes>> getFosterDetail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<FosterDetailRes> response = fosterService.getFosterDetail(id, principal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @PutMapping(FosterApi.BY_ID)
    public ResponseEntity<ResponseDto<Void>> updateFoster(
            @PathVariable Long id, @RequestBody FosterUpdateReq req,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<Void> response = fosterService.updateFoster(id, req, principal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
