package org.example.miniprojpetadoptionshelter.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.*;
import org.example.miniprojpetadoptionshelter.dto.auth.response.LoginResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.PasswordVerifyResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.SignupResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

public interface AuthService {

    ResponseDto<SignupResponseDto> signup(SignupRequestDto request);

    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto request, HttpServletResponse response);

    ResponseDto<Void> logout(HttpServletResponse response, UserPrincipal principal);

    ResponseDto<LoginResponseDto> refresh(HttpServletRequest request, HttpServletResponse response);

    ResponseDto<Void> resetPassword(@Valid PasswordResetRequestDto request, UserPrincipal principal);

    ResponseDto<PasswordVerifyResponseDto> verify(String token);

    ResponseDto<Void> sendPasswordResetEmail(String email);
}
