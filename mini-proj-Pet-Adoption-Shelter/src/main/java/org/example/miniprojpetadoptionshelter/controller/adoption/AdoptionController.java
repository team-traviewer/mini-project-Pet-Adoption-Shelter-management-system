package org.example.miniprojpetadoptionshelter.controller.adoption;

import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/adoptions")
public class AdoptionController {

    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<AdoptionResponse> create(
            @AuthenticationPrincipal CustomUser user,
            @RequestBody AdoptionCreateRequest request
    ) {
        return ResponseEntity.ok(
                adoptionService.createAdoption(user.getId(), request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionResponse> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(adoptionService.getAdoption(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionResponse> update(
            @PathVariable Long id,
            @RequestBody AdoptionUpdateRequest request
    ) {
        return ResponseEntity.ok(adoptionService.updateAdoption(id, request));
    }
}