package org.example.miniprojpetadoptionshelter.dto.Adoptions.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateAdoptionRequest {
    private LocalDate adoptionDate;
    private Integer fee;
    private String contractPDF;
}