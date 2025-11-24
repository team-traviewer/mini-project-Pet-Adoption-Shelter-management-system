package org.example.miniprojpetadoptionshelter.service.adoption;


import org.example.miniprojpetadoptionshelter.dto.Adoption.request.AdoptionCreateRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoption.request.AdoptionUpdateRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoption.response.AdoptionResponse;

public interface AdoptionService {
    AdoptionResponse createAdoption(Long userId, AdoptionCreateRequest request);
    AdoptionResponse getAdoption(Long adoptionId);
    AdoptionResponse updateAdoption(Long adoptionId, AdoptionUpdateRequest request);
}