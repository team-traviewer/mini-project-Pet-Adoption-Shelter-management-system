package org.example.miniprojpetadoptionshelter.controller.auth;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.auth.AuthApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.LoginRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.LogoutRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.PasswordResetRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.RefreshRequestDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.LoginResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.PasswordVerifyResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.SignupResponseDto;
import org.example.miniprojpetadoptionshelter.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping(AuthApi.SIGNUP)
    public ResponseEntity<ResponseDto<SignupResponseDto>> signup(
            @Valid @RequestBody SignupResponseDto request
    ) {
        ResponseDto<SignupResponseDto> response = authService.signup(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 로그인
    @PostMapping(AuthApi.LOGIN)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto request
    ) {
        ResponseDto<LoginResponseDto> response = authService.login(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 로그아웃
    @PostMapping(AuthApi.LOGOUT)
    public ResponseEntity<ResponseDto<Void>> logout(
            @Valid @RequestBody LogoutRequestDto request
    ) {
        ResponseDto<Void> response = authService.logout(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 토큰 재발급
    public ResponseEntity<ResponseDto<LoginResponseDto>> refresh(
            @Valid @RequestBody RefreshRequestDto request
    ) {
       ResponseDto<LoginResponseDto> response = authService.refresh(request);
       return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 비밀번호 재설정
    @PostMapping(AuthApi.PASSWORD_RESET)
    public ResponseEntity<ResponseDto<Void>> resetPassword(
            @Valid @RequestBody PasswordResetRequestDto request
    ) {
       ResponseDto<Void> response = authService.resetPassword(request);
       return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 비밀번호 재설정 토큰 유효성 확인
    @PostMapping(AuthApi.VERIFY)
    public ResponseEntity<ResponseDto<PasswordVerifyResponseDto>> verify(
            @RequestParam("token") String token
    ) {
        ResponseDto<PasswordVerifyResponseDto> response = authService.verify(token);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
