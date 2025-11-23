package org.example.miniprojpetadoptionshelter.dto.Adoption.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdoptionCreateRequest {
    private Long animalId;
    private Long applicationId;
    private Long adopterId;
    private String adoptionDate;
    private Integer fee;
    private String contractPDF;
}