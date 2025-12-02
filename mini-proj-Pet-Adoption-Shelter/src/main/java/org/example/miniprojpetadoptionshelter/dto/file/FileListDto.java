package org.example.miniprojpetadoptionshelter.dto.file;

import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;

public record FileListDto(
        Long fileId,
        String originalName,
        String storedName,
        String contentType,
        Long fileSize,
        String downloadUrl
) {
    public abstract static FileListDto fromEntity() {}
}
