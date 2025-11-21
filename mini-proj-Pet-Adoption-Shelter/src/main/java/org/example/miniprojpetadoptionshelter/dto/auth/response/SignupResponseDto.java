package org.example.miniprojpetadoptionshelter.dto.auth.response;


public record SignupResponseDto(
        String loginId,
        String name,
        String email,
        String phone
) {

}
