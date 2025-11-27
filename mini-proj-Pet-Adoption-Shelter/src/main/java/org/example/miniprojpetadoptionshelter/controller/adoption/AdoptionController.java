package org.example.miniprojpetadoptionshelter.controller.adoption;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.adoption.AdoptionApi;
import org.example.miniprojpetadoptionshelter.dto.adoption.request.AdoptionCreateRequest;
import org.example.miniprojpetadoptionshelter.dto.adoption.request.AdoptionUpdateRequest;
import org.example.miniprojpetadoptionshelter.dto.adoption.response.AdoptionResponse;
import org.example.miniprojpetadoptionshelter.security.user.CustomUser;
import org.example.miniprojpetadoptionshelter.service.adoption.AdoptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AdoptionApi.ROOT)
public class AdoptionController {

    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<AdoptionResponse> createAdoption(
            @AuthenticationPrincipal CustomUser user,
            @RequestBody AdoptionCreateRequest request
    ) {
        Long adopterId = user.getId();
        AdoptionResponse response = adoptionService.createAdoption(adopterId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(AdoptionApi.BY_ID)
    public ResponseEntity<AdoptionResponse> getAdoptionById(
            @PathVariable Long adoptionId
    ) {
        AdoptionResponse response = adoptionService.getAdoption(adoptionId);
        return ResponseEntity.ok(response);
    }

    @PutMapping(AdoptionApi.UPDATE)
    public ResponseEntity<AdoptionResponse> updateAdoption(
            @PathVariable Long adoptionId,
            @RequestBody AdoptionUpdateRequest request
    ) {
        AdoptionResponse response = adoptionService.updateAdoption(adoptionId, request);
        return ResponseEntity.ok(response);
    }
}