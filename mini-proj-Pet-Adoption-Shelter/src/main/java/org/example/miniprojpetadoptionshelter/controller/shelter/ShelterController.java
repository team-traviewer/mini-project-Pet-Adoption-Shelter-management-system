package org.example.miniprojpetadoptionshelter.controller.shelter;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.adoption.AdoptionApi;
import org.example.miniprojpetadoptionshelter.common.apis.shelter.ShelterApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.shelter.ShelterCreateReq;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.shelter.ShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ShelterController {

    private final ShelterService shelterService;

    @PostMapping(ShelterApi.ROOT)
    public ResponseEntity<ResponseDto<ShelterRes>> createShelter(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody ShelterCreateReq request
            ) {
        ResponseDto<ShelterRes> response = shelterService.createShelter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(ShelterApi.ROOT)
    public ResponseEntity<ResponseDto<List<ShelterListRes>>> getAllShelter() {
        ResponseDto<List<ShelterListRes>> response = shelterService.getAllShelters();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(ShelterApi.BY_ID)
    public ResponseEntity<ResponseDto<ShelterDetailRes>> getShelterById(
            @PathVariable Long shelterId
    ) {
        ResponseDto<ShelterDetailRes> response = shelterService.getShelterById(shelterId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
