package org.example.miniprojpetadoptionshelter.dto.file;

import java.time.LocalDateTime;

public record FileInfoRes (
        Long id,
        String originalName,
        String storedName,
        String contentType,
        Long fileSize,
        String fileUrl,
        LocalDateTime createdAt
) {
}
