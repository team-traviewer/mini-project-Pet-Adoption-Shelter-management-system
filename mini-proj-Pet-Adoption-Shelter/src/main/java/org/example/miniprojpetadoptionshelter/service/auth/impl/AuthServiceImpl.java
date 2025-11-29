package org.example.miniprojpetadoptionshelter.service.auth.impl;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.LoginRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.LogoutRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.PasswordResetRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.RefreshRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.LoginResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.PasswordVerifyResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.SignupResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.auth.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public ResponseDto<SignupResponseDto> signup(SignupResponseDto request) {
        return null;
    }

    @Override
    public ResponseDto<LoginResponseDto> login(LoginRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<Void> logout(LogoutRequestDto request, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<LoginResponseDto> refresh(RefreshRequestDto request, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<Void> resetPassword(PasswordResetRequestDto request, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<PasswordVerifyResponseDto> verify(String token, UserPrincipal principal) {
        return null;
    }
}
