package org.example.miniprojpetadoptionshelter.dto.Adoptions.response;

import lombok.Builder;
import lombok.Getter;
import org.example.miniprojpetadoptionshelter.entity.adoptions.Adoption;

@Getter
@Builder
public class AdoptionResponse {
    private Long id;
    private Long animalId;
    private Long applicationId;
    private Long adopterId;

    private String adoptionDate;
    private Integer fee;
    private String contractPDF;

    public static AdoptionResponse fromEntity(Adoption adoption) {
        return AdoptionResponse.builder()
                .id(adoption.getId())
                .animalId(adoption.getAnimal().getId())
                .applicationId(adoption.getApplication().getId())
                .adopterId(adoption.getAdopter().getId())
                .adoptionDate(adoption.getAdoptionDate().toString())
                .fee(adoption.getFee())
                .contractPDF(adoption.getContractPDF())
                .build();
    }
}