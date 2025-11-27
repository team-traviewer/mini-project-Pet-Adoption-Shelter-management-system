package org.example.miniprojpetadoptionshelter.dto.shelter;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ShelterCreateReq(
        String name,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        String phone,
        MultipartFile image
) {
}
