package org.example.miniprojpetadoptionshelter.dto.Adoption.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AdoptionResponse {

    private Long adoptionId;
    private Long animalId;
    private Long applicationId;
    private Long adopterId;

    private String adoptionDate;
    private Integer fee;
    private String contractPDF;

    private String createdAt;
    private String updatedAt;

    private String message;
}
