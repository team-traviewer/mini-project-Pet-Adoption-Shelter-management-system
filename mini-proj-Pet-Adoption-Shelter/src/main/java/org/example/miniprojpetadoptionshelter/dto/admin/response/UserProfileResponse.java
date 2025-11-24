package org.example.miniprojpetadoptionshelter.dto.admin.response;

import org.example.miniprojpetadoptionshelter.entity.user.User;

import java.time.LocalDateTime;

public record UserProfileResponse(
        String loginId,
        String name,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getLoginId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
