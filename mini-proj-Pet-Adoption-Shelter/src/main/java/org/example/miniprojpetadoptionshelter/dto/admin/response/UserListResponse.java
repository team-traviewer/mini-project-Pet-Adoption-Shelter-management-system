package org.example.miniprojpetadoptionshelter.dto.admin.response;

import org.example.miniprojpetadoptionshelter.entity.user.User;

import java.time.LocalDateTime;

public record UserListResponse(
    String loginId,
    String name,
    LocalDateTime createdAt
) {
    public static UserListResponse from(User user) {
        return new UserListResponse(
                user.getLoginId(),
                user.getName(),
                user.getCreatedAt()
        );
    }
}
