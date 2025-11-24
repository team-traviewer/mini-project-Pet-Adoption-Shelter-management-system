package org.example.miniprojpetadoptionshelter.service.adoption.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.Adoption.request.AdoptionCreateRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoption.request.AdoptionUpdateRequest;
import org.example.miniprojpetadoptionshelter.dto.Adoption.response.AdoptionResponse;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.entity.adoptions.Adoption;
import org.example.miniprojpetadoptionshelter.entity.adoptions.Application;
import org.example.miniprojpetadoptionshelter.repository.adoption.AdoptionRepository;
import org.example.miniprojpetadoptionshelter.repository.animal.AnimalRepository;
import org.example.miniprojpetadoptionshelter.repository.application.ApplicationRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.service.adoption.AdoptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final AnimalRepository animalRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Override
    public AdoptionResponse createAdoption(Long userId, AdoptionCreateRequest request) {

        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("동물 없음"));

        Application app = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new RuntimeException("입양 신청 없음"));

        User adopter = userRepository.findById(request.getAdopterId())
                .orElseThrow(() -> new RuntimeException("입양자 없음"));

        Adoption adoption = Adoption.builder()
                .animal(animal)
                .application(app)
                .adopter(adopter)
                .adoptionDate(LocalDate.parse(request.getAdoptionDate()))
                .fee(request.getFee())
                .contractPDF(request.getContractPDF())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        adoptionRepository.save(adoption);

        return AdoptionResponse.builder()
                .message("입양 계약 생성 완료")
                .adoptionId(adoption.getId())
                .animalId(animal.getId())
                .applicationId(app.getId())
                .adopterId(adopter.getId())
                .adoptionDate(adoption.getAdoptionDate().toString())
                .fee(adoption.getFee())
                .contractPDF(adoption.getContractPDF())
                .createdAt(adoption.getCreatedAt().toString())
                .build();
    }

    @Override
    public AdoptionResponse getAdoption(Long id) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("계약 없음"));

        return AdoptionResponse.builder()
                .message("입양 계약 조회 성공")
                .adoptionId(adoption.getId())
                .animalId(adoption.getAnimal().getId())
                .applicationId(adoption.getApplication().getId())
                .adopterId(adoption.getAdopter().getId())
                .adoptionDate(adoption.getAdoptionDate().toString())
                .fee(adoption.getFee())
                .contractPDF(adoption.getContractPDF())
                .createdAt(adoption.getCreatedAt().toString())
                .updatedAt(adoption.getUpdatedAt().toString())
                .build();
    }

    @Override
    public AdoptionResponse updateAdoption(Long id, AdoptionUpdateRequest request) {

        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("계약 없음"));

        if (request.getAdoptionDate() != null)
            adoption.setAdoptionDate(LocalDate.parse(request.getAdoptionDate()));

        if (request.getFee() != null)
            adoption.setFee(request.getFee());

        if (request.getContractPDF() != null)
            adoption.setContractPDF(request.getContractPDF());

        adoption.setUpdatedAt(LocalDateTime.now());

        adoptionRepository.save(adoption);

        return AdoptionResponse.builder()
                .message("입양 계약 수정 완료")
                .adoptionId(adoption.getId())
                .build();
    }
}