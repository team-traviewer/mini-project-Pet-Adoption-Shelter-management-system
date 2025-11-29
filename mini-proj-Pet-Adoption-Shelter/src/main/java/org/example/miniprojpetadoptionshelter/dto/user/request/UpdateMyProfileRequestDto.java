package org.example.miniprojpetadoptionshelter.dto.user.request;

import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;

public record UpdateMyProfileRequestDto(
        String name,
        String email,
        String phone,
        FileInfo profileFile
) {
}
