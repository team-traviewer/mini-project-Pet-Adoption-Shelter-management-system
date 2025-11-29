package org.example.miniprojpetadoptionshelter.service.auth;

import jakarta.validation.Valid;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.LoginRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.LogoutRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.PasswordResetRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.RefreshRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.LoginResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.PasswordVerifyResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.SignupResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

public interface AuthService {

    ResponseDto<SignupResponseDto> signup(@Valid SignupResponseDto request);

    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto request);

    ResponseDto<Void> logout(@Valid LogoutRequestDto request, UserPrincipal principal);

    ResponseDto<LoginResponseDto> refresh(@Valid RefreshRequestDto request, UserPrincipal principal);

    ResponseDto<Void> resetPassword(@Valid PasswordResetRequestDto request, UserPrincipal principal);

    ResponseDto<PasswordVerifyResponseDto> verify(String token, UserPrincipal principal);
}
