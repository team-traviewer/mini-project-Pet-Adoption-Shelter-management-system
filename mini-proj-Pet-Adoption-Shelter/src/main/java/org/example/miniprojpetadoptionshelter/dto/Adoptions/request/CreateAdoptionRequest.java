package org.example.miniprojpetadoptionshelter.dto.Adoptions.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateAdoptionRequest {
    private Long animalId;
    private Long applicationId;
    private Long adopterId;

    private LocalDate adoptionDate;
    private Integer fee;
    private String contractPDF;
}