package org.example.miniprojpetadoptionshelter.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.common.enums.AuthProvider;
import org.example.miniprojpetadoptionshelter.common.enums.RoleType;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;
import org.example.miniprojpetadoptionshelter.entity.file.FileInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_login", columnNames = "username"),
                @UniqueConstraint(name = "uk_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_name", columnNames = "name"),
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "login_id", nullable = false, updatable = false, length = 50)
    private String loginId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_file_id",
            foreignKey = @ForeignKey(name = "fk_users_profile_file"))
    private FileInfo profileFile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    // 1) 가입 경로 (LOCAL / GOOGLE / KAKAO)
    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 20, nullable = false)
    private AuthProvider provider;

    // 2) 각 provider가 주는 유니크 ID
    @Column(name = "provider_id", length = 100)
    private String providerId;

    // 3) 이메일 인증 여부 (소셜은 대부분 true 처리)
    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;


    @Builder
    private User(
            String loginId,
            String password,
            String email,
            String name,
            String phone,
            FileInfo profileFile,
            AuthProvider provider,
            String providerId,
            boolean emailVerified
    ) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.profileFile = profileFile;
        this.provider = provider;
        this.providerId = providerId;
        this.emailVerified = emailVerified;
    }

    // OAuth2용 생성/업데이트 메서드
    public static User createOauthUser(
            AuthProvider provider,
            String providerId,
            String email,
            String name,
            String phone
    ) {
        return User.builder()
                .loginId(provider.name() + "_" + providerId)
                .password(null)
                .email(email)
                .name(name)
                .phone(phone)
                .provider(provider)
                .providerId(providerId)
                .emailVerified(true)
                .build();
    }

    public void changePassword(String password) { this.password = password; }

    public void updateProfile(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void grantRole(Role role) {
        boolean exists = userRoles.stream()
                .anyMatch(userRole -> userRole.getRole().equals(role));
        if(!exists) {
            userRoles.add(new UserRole(this, role));
        }
    }

    public void revokeRole(Role role) {
        userRoles.removeIf(userRole -> userRole.getRole().equals(role));
    }

    public Set<RoleType> getAllRoles() {
        return userRoles.stream()
                .map(UserRole::getRole)
                .map(Role::getRoleName)
                .collect(Collectors.toUnmodifiableSet());
    }
}




