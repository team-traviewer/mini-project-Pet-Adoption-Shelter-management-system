package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.fromAnimal.IntakeApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.IntakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntakeController {

    private final IntakeService intakeService;

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping(IntakeApi.INTAKE_ANIMAL)
    public ResponseEntity<ResponseDto<Void>> createIntake(
            @RequestBody IntakeCreateReq req,
            @AuthenticationPrincipal UserPrincipal principal
            ) {
        ResponseDto<Void> response = intakeService.createIntake(req, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(IntakeApi.INTAKE_ANIMAL)
    public ResponseEntity<ResponseDto<List<IntakeListRes>>> getIntakeList(
            @RequestParam Long animalId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<List<IntakeListRes>> response = intakeService.getIntakeList(animalId, principal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @GetMapping(IntakeApi.BY_ID)
    public ResponseEntity<ResponseDto<IntakeDetailRes>> getIntakeDetail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<IntakeDetailRes> response = intakeService.getIntakeDetail(id, principal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @PutMapping(IntakeApi.BY_ID)
    public ResponseEntity<ResponseDto<Void>> updateIntake(
            @PathVariable Long id, @RequestBody IntakeUpdateReq req,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<Void> response = intakeService.updateIntake(id, req, principal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
