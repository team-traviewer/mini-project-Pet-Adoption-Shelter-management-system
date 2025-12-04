package org.example.miniprojpetadoptionshelter.controller.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.auth.AuthApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.*;
import org.example.miniprojpetadoptionshelter.dto.auth.response.LoginResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.PasswordVerifyResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.SignupResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping(AuthApi.SIGNUP)
    public ResponseEntity<ResponseDto<SignupResponseDto>> signup(
            @Valid @RequestBody SignupRequestDto request
    ) {
        ResponseDto<SignupResponseDto> response = authService.signup(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 로그인
    @PostMapping(AuthApi.LOGIN)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto request,
            HttpServletResponse response
    ) {
        ResponseDto<LoginResponseDto> result = authService.login(request, response);
        return ResponseEntity.status(response.getStatus()).body(result);
    }

    // 로그아웃
    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @PostMapping(AuthApi.LOGOUT)
    public ResponseEntity<ResponseDto<Void>> logout(
            HttpServletResponse response,
            @AuthenticationPrincipal UserPrincipal principal
            ) {
        ResponseDto<Void> result = authService.logout(response, principal);
        return ResponseEntity.status(response.getStatus()).body(result);
    }

    // 토큰 재발급
    @PostMapping(AuthApi.REFRESH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
       ResponseDto<LoginResponseDto> result = authService.refresh(request, response);
       return ResponseEntity.status(response.getStatus()).body(result);
    }

    // 비밀번호 재설정
    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @PostMapping(AuthApi.PASSWORD_RESET)
    public ResponseEntity<ResponseDto<Void>> resetPassword(
            @Valid @RequestBody PasswordResetRequestDto request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
       ResponseDto<Void> response = authService.resetPassword(request, principal);
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
