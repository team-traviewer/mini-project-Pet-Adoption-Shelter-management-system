package org.example.miniprojpetadoptionshelter.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;
import org.example.miniprojpetadoptionshelter.entity.user.User;

public record SignupRequestDto(
        @NotBlank(message = "아이디는 필수입니다.")
        @Size(min = 4, max = 50, message = "아이디는 4 ~ 50자 사이여야 합니다.")
        String loginId,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 100, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        String password,

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, max = 8, message = "이름은 2 ~ 8자 사이여야 합니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "휴대폰 번호는 필수입니다.")
        String phone
) {
        public User toEntity(String encodedPassword, FileInfo profileFile) {
                return User.builder()
                        .loginId(loginId)
                        .password(encodedPassword)
                        .name(name)
                        .email(email)
                        .phone(phone)
                        .build();
        }
}
