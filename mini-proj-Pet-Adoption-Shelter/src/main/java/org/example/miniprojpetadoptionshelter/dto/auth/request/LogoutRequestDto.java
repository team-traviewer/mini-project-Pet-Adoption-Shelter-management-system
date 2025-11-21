package org.example.miniprojpetadoptionshelter.dto.auth.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequestDto(
        @NotBlank(message = "리프레시 토큰은 필수 입니다.")
        String refreshToken
) {
}
