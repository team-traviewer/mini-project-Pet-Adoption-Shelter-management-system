package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.common.enums.RoleType;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.time.LocalDateTime;
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
@Builder
@AllArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    public User(String loginId, String name , String password, String email, String phone) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
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




