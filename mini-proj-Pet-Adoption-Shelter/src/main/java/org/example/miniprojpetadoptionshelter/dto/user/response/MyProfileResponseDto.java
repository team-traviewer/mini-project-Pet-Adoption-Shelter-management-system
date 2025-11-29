package org.example.miniprojpetadoptionshelter.dto.user.response;

import org.example.miniprojpetadoptionshelter.common.enums.Gender;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;
import org.example.miniprojpetadoptionshelter.entity.user.User;

import java.time.LocalDateTime;

public record MyProfileResponseDto(
        String name,
        String email,
        String phone,
        Gender gender,
        FileInfo profileFile,
        LocalDateTime createdAt
) {
    public MyProfileResponseDto from(User user) {
        return new MyProfileResponseDto(
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getGender(),
                user.getProfileFile(),
                user.getCreatedAt()
        );
    }
}
