package org.example.miniprojpetadoptionshelter.dto.adoption.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionUpdateRequest {
    private String adoptionDate;
    private Integer fee;
    private String contractPDF;
}