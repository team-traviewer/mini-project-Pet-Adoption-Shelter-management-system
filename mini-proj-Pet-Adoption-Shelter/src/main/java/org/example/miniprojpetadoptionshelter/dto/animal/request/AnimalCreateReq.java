package org.example.miniprojpetadoptionshelter.dto.animal.request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record AnimalCreateReq(
        Long shelterId,
        String name,
        String species,
        String breed,
        String sex,
        BigDecimal ageYears,
        BigDecimal weightKg,
        String temperament,
        String status,
        List<MultipartFile> files
) {

}
