package org.example.miniprojpetadoptionshelter.dto.animal.response;

import org.example.miniprojpetadoptionshelter.dto.file.FileInfoRes;

public record AnimalListRes(
        Long id,
        Long shelterId,
        String name,
        String species,
        FileInfoRes thumbnail
) {
}
