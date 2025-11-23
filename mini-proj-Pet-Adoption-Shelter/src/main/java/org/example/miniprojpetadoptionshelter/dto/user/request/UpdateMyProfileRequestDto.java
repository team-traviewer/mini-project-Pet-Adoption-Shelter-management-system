package org.example.miniprojpetadoptionshelter.dto.user.request;

public record UpdateMyProfileRequestDto(
        String name,
        String email,
        String phone
) {
}
