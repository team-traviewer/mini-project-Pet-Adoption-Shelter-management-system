package org.example.miniprojpetadoptionshelter.dto.auth.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "id 값은 필수 입니다.")
        String loginId,

        @NotBlank(message = "password 값은 필수 입니다.")
        String password
) {
}
