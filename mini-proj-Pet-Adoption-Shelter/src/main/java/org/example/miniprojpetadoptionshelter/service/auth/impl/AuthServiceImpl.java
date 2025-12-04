package org.example.miniprojpetadoptionshelter.service.auth.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojpetadoptionshelter.common.enums.ErrorCode;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.request.*;
import org.example.miniprojpetadoptionshelter.dto.auth.response.LoginResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.PasswordVerifyResponseDto;
import org.example.miniprojpetadoptionshelter.dto.auth.response.SignupResponseDto;
import org.example.miniprojpetadoptionshelter.entity.auth.RefreshToken;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.exception.BusinessException;
import org.example.miniprojpetadoptionshelter.repository.auth.RefreshTokenRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.provider.JwtProvider;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipalMapper;
import org.example.miniprojpetadoptionshelter.security.util.CookieUtils;
import org.example.miniprojpetadoptionshelter.service.auth.AuthService;
import org.example.miniprojpetadoptionshelter.service.auth.EmailService;
import org.example.miniprojpetadoptionshelter.service.file.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;
    private final FileService fileService;
    private final AuthenticationManager authenticationManager;
    private final UserPrincipalMapper userPrincipalMapper;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("http://localhost:5173/oauth2/callback")
    private String redirectUri;

    private static final String REFRESH_TOKEN = "refreshToken";

    @Override
    public ResponseDto<SignupResponseDto> signup(SignupRequestDto request) {

        if (userRepository.findByLoginId(request.loginId()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER);
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER);
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        FileInfo fileInfo = fileService.getDefaultProfile(); // 기본 프로필 사진 넣기

        User newUser = request.toEntity(encodedPassword, fileInfo);

        userRepository.save(newUser);

        String emailToken = jwtProvider.generateEmailJwtToken(request.email(), "VERIFY_EMAIL");

        String verifyUrl = "https://myservice.com/email/verify?token=" + emailToken;

        emailService.sendHtmlEmail(
          request.email(),
          "회원가입 이메일 인증",
                """
                <div style="padding: 20px"; font-size:16px;">
                    <p>회원가입을 환영합니다!</p>
                    <p>아래 버튼을 눌러 이메일 인증을 완료해주세요.</p>
                    <a href="%s"
                        style="display:inline-block; padding:10px 20px; background:#2a5dff;
                        color: white; text-decoration: none; border-radius:8px; margin-top:10px;">
                        이메일 인증하기
                    </a>
                </div>
                """.formatted(verifyUrl)
        );
        return ResponseDto.success(
                "회원가입 완료",
                SignupResponseDto.from(newUser)
        );
    }

    @Override
    public ResponseDto<LoginResponseDto> login(LoginRequestDto request, HttpServletResponse response) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(
                    request.loginId(), request.password()
            );

            var authentication = authenticationManager.authenticate(authToken);

            String loginId = authentication.getName();

            var principal = userPrincipalMapper.toPrincipal(loginId);
            Set<String> roles = principal.getAuthorities()
                    .stream()
                    .map(a -> a.getAuthority())
                    .collect(Collectors.toSet());

            String accessToken = jwtProvider.generateAccessToken(loginId, roles);
            String refreshToken = jwtProvider.generateRefreshToken(loginId, roles);

            long accessExpiresIn = jwtProvider.getRemainingMillis(accessToken);
            long refreshRemaining = jwtProvider.getRemainingMillis(refreshToken);

            Instant refreshExpiry = Instant.now().plusMillis(refreshRemaining);

            User user = userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

            refreshTokenRepository.findByUser(user)
                    .ifPresentOrElse(
                            r -> {
                                r.renew(refreshToken, refreshExpiry);
                                refreshTokenRepository.save(r);
                            },
                            () -> {
                                RefreshToken r = RefreshToken.builder()
                                        .user(user)
                                        .token(refreshToken)
                                        .expiry(refreshExpiry)
                                        .build();
                                refreshTokenRepository.save(r);
                            }
                    );

            CookieUtils.addHttpOnlyCookie(
                    response,
                    REFRESH_TOKEN,
                    refreshToken,
                    (int) (refreshRemaining / 10000),
                    true
            );

            return ResponseDto.success(
                    "로그인 성공",
                    LoginResponseDto.of(accessToken, accessExpiresIn)
            );

        } catch (BadCredentialsException ex) {
            throw new BusinessException(ErrorCode.AUTHENTICATION_FAILED);
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto<Void> logout(HttpServletResponse response, UserPrincipal principal) {
        Long id = principal.getId();
        userRepository.findById(id).ifPresent(user -> {
            refreshTokenRepository.deleteByUser(user);
        });

        CookieUtils.deleteCookie(response, REFRESH_TOKEN);

        return ResponseDto.success("로그아웃 완료");
    }

    @Override
    public ResponseDto<LoginResponseDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_EXPIRED));

        if (!jwtProvider.isValidToken(refreshToken)) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }

        String loginId = jwtProvider.getUsernameFromJwt(refreshToken);

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RefreshToken stored = refreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_INVALID));

        if (!stored.getToken().equals(refreshToken)) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID, "Refresh token mismatch");
        }

        var principal = userPrincipalMapper.map(user);
        Set<String> roles = principal.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toSet());

        String newAccess = jwtProvider.generateAccessToken(loginId, roles);
        String newRefresh = jwtProvider.generateRefreshToken(loginId, roles);

        long accessExpiresIn = jwtProvider.getRemainingMillis(newAccess);
        long refreshRemaining = jwtProvider.getRemainingMillis(newRefresh);

        stored.renew(newRefresh, Instant.now().plusMillis(refreshRemaining));
        refreshTokenRepository.save(stored);

        CookieUtils.addHttpOnlyCookie(
                response,
                REFRESH_TOKEN,
                newRefresh,
                (int) (refreshRemaining) / 1000,
                false
        );

        return ResponseDto.success(
                "토큰 재발급 완료",
                LoginResponseDto.of(newAccess, accessExpiresIn)
        );
    }

    @Override
    public ResponseDto<Void> resetPassword(PasswordResetRequestDto request, UserPrincipal principal) {
        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_CONFIRM_MISMATCH);
        }

        String token = request.token();

        if (!jwtProvider.isValidToken(token)) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }

        String email = jwtProvider.getEmailFromEmailToken(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.changePassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        refreshTokenRepository.findByUser(user)
                .ifPresent(refreshTokenRepository::delete);

        return ResponseDto.success("비밀번호 재설정 완료");
    }

    @Override
    public ResponseDto<PasswordVerifyResponseDto> verify(String token) {

        if (!jwtProvider.isValidToken(token)) {
            return ResponseDto.success(PasswordVerifyResponseDto.failure());
        }

        String email = jwtProvider.getEmailFromEmailToken(token);
        return ResponseDto.success(PasswordVerifyResponseDto.success(email));
    }

    @Override
    public ResponseDto<Void> sendPasswordResetEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        String token = jwtProvider.generateEmailJwtToken(email, "RESET_PASSWORD");

        String url = "https://myservice.com/reset-password?token=" + token;

        emailService.sendPasswordReset(email, url);

        return ResponseDto.success("비밀번호 재설정 이메일 전송 완료");
    }


}
