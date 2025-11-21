package org.example.miniprojpetadoptionshelter.controller;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.Adoptions.request.CreateAdoptionRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoptions.request.UpdateAdoptionRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoptions.response.AdoptionResponse;
import org.example.miniprojpetadoptionshelter.service.AdoptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    @GetMapping("/{id}")
    public AdoptionResponse get(@PathVariable Long id) {
        return adoptionService.getAdoption(id);
    }

    @PostMapping
    public Long create(@RequestBody CreateAdoptionRequest req) {
        return adoptionService.createAdoption(req);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateAdoptionRequest req) {
        adoptionService.updateAdoption(id, req);
    }
}