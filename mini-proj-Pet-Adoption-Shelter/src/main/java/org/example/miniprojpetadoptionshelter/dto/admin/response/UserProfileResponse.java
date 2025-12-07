package org.example.miniprojpetadoptionshelter.dto.admin.response;

import org.example.miniprojpetadoptionshelter.common.enums.Gender;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;
import org.example.miniprojpetadoptionshelter.entity.user.User;

import java.time.LocalDateTime;

public record UserProfileResponse(
        String loginId,
        String name,
        String email,
        String phone,
        Gender gender,
        FileInfo profileFile,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getLoginId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getGender(),
                user.getProfileFile(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
