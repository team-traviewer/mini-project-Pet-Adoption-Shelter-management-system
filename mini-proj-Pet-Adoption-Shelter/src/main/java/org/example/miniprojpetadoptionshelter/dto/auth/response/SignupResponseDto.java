package org.example.miniprojpetadoptionshelter.dto.auth.response;


import org.example.miniprojpetadoptionshelter.entity.user.User;

public record SignupResponseDto(
        Long id,
        String loginId,
        String name,
        String email,
        String phone
) {
    public static SignupResponseDto from(User user) {
        return new SignupResponseDto(
                user.getId(),
                user.getLoginId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
