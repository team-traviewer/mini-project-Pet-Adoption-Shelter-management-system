package org.example.miniprojpetadoptionshelter.dto.shelter.response;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ShelterDetailRes(
        String name,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        String phone,
        MultipartFile image
    ) { }
