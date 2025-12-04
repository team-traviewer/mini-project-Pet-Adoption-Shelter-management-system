package org.example.miniprojpetadoptionshelter.dto.animal;

import org.example.miniprojpetadoptionshelter.dto.file.FileListDto;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;

public record AnimalFileListDto(
        Long fileId,
        String originalName,
        String storedName,
        String contentType,
        Long fileSize
) {
    public static AnimalFileListDto fromEntity(FileInfo fileInfo) {
        if (fileInfo == null) return null;
        return new AnimalFileListDto(
                fileInfo.getId(),
                fileInfo.getOriginalName(),
                fileInfo.getStoredName(),
                fileInfo.getContentType(),
                fileInfo.getFileSize());
    }
}
