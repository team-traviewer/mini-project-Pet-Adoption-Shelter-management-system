package org.example.miniprojpetadoptionshelter.service;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.Adoptions.request.CreateAdoptionRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoptions.request.UpdateAdoptionRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoptions.response.AdoptionResponse;
import org.example.miniprojpetadoptionshelter.entity.Animal;
import org.example.miniprojpetadoptionshelter.entity.adoptions.Adoption;
import org.example.miniprojpetadoptionshelter.entity.adoptions.Application;
import org.example.miniprojpetadoptionshelter.entity.adoptions.User;
import org.example.miniprojpetadoptionshelter.repository.AdoptionRepository;
import org.example.miniprojpetadoptionshelter.repository.AnimalRepository;
import org.example.miniprojpetadoptionshelter.repository.ApplicationRepository;
import org.example.miniprojpetadoptionshelter.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final AnimalRepository animalRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public AdoptionResponse getAdoption(Long id) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption not found"));
        return AdoptionResponse.fromEntity(adoption);
    }


    @Transactional
    public Long createAdoption(CreateAdoptionRequest req) {
        Animal animal = animalRepository.findById(req.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        Application application = applicationRepository.findById(req.getApplicationId())
                .orElseThrow(() -> new RuntimeException("Application not found"));
        User adopter = userRepository.findById(req.getAdopterId())
                .orElseThrow(() -> new RuntimeException("Adopter not found"));

        Adoption adoption = Adoption.builder()
                .animal(animal)
                .application(application)
                .adopter(adopter) // 이제 정상 적용
                .adoptionDate(req.getAdoptionDate())
                .fee(req.getFee())
                .contractPDF(req.getContractPDF())
                .build();

        adoptionRepository.save(adoption);
        return adoption.getId();
    }


    @Transactional
    public void updateAdoption(Long id, UpdateAdoptionRequest req) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption not found"));

        adoption.update(
                req.getAdoptionDate(),
                req.getFee(),
                req.getContractPDF()
        );
    }
}