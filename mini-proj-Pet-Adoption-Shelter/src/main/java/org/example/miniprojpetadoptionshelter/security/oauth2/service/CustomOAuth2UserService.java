package org.example.miniprojpetadoptionshelter.security.oauth2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojpetadoptionshelter.common.enums.AuthProvider;
import org.example.miniprojpetadoptionshelter.common.enums.RoleType;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;
import org.example.miniprojpetadoptionshelter.entity.user.Role;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.repository.user.RoleRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.oauth2.user.GoogleOAuth2UserInfo;
import org.example.miniprojpetadoptionshelter.security.oauth2.user.KakaoOAuth2UserInfo;
import org.example.miniprojpetadoptionshelter.security.oauth2.user.OAuth2UserInfo;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipalMapper;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserPrincipalMapper userPrincipalMapper;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = mapProvider(registrationId);

        OAuth2UserInfo userInfo = convertToUserInfo(provider, oAuth2User.getAttributes());

        User user = upsertUser(provider, userInfo);

        return userPrincipalMapper.toPrincipal(user.getLoginId());
    }

    private AuthProvider mapProvider(String registrationId) {
        return switch (registrationId.toLowerCase()) {
            case "google" -> AuthProvider.GOOGLE;
            case "kakao" -> AuthProvider.KAKAO;
            default -> throw new IllegalArgumentException("지원하지 않는 provider: " + registrationId);
        };
    }

    private OAuth2UserInfo convertToUserInfo(AuthProvider provider, Map<String, Object> attributes) {
        return switch (provider) {
            case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
            case KAKAO -> new KakaoOAuth2UserInfo(attributes);
            default -> throw new IllegalArgumentException("지원하지 않는 provider: " + provider);
        };
    }

    @Transactional
    protected User upsertUser(org.example.miniprojpetadoptionshelter.common.enums.AuthProvider provider, OAuth2UserInfo userInfo) {
        String providerId = userInfo.getId();

        String email = userInfo.getEmail();
        String name = userInfo.getName();
        String phone = userInfo.getPhone();


        return userRepository.findByProviderAndProviderId(provider, providerId)
                .map(user -> {
                    user.updateProfile(name, email, phone);
                    return user;
                })
                .orElseGet(() ->{
                    User newUser = User.createOauthUser(
                            provider,
                            providerId,
                            email,
                            name,
                            phone
                    );

                    // 기본 권한 ROLE_USER를 DB에서 조회
                    Role userRole = roleRepository
                            .findById(RoleType.USER)
                            .orElseThrow(() -> new IllegalArgumentException("ROLE_USER가 DB에 없습니다."));
                    // 생성된 유저에게 ROLE_USER 권한 부여
                    newUser.grantRole(userRole);

                    return userRepository.save(newUser);
                });
    }

}
