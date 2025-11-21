package org.example.miniprojpetadoptionshelter.dto.user.response;

import org.example.miniprojpetadoptionshelter.entity.User;

import java.time.LocalDateTime;

public record MyProfileResponseDto(
        String name,
        String email,
        String phone,
        LocalDateTime createdAt
) {
    public MyProfileResponseDto from(User user) {
        return new MyProfileResponseDto(
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedAt()
        );
    }
}
