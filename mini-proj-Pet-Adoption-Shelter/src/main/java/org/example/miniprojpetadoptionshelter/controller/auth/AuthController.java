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
    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @PostMapping(AuthApi.LOGOUT)
    public ResponseEntity<ResponseDto<Void>> logout(
            @Valid @RequestBody LogoutRequestDto request,
            @AuthenticationPrincipal UserPrincipal principal
            ) {
        ResponseDto<Void> response = authService.logout(request, principal);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 토큰 재발급
    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @PostMapping(AuthApi.REFRESH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> refresh(
            @Valid @RequestBody RefreshRequestDto request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
       ResponseDto<LoginResponseDto> response = authService.refresh(request, principal);
       return ResponseEntity.status(response.getStatus()).body(response);
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
    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @PostMapping(AuthApi.VERIFY)
    public ResponseEntity<ResponseDto<PasswordVerifyResponseDto>> verify(
            @RequestParam("token") String token,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<PasswordVerifyResponseDto> response = authService.verify(token, principal);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
