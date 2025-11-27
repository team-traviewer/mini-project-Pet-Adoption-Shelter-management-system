package org.example.miniprojpetadoptionshelter.dto.animal.response;

import org.example.miniprojpetadoptionshelter.dto.file.FileINfoRes;

public record AnimalListRes(
        Long id,
        Long shelterId,
        String name,
        String species,
        FileINfoRes thumbnail
) {
}
