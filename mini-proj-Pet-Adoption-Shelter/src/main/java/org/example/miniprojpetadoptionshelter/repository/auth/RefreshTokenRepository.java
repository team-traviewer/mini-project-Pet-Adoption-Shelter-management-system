package org.example.miniprojpetadoptionshelter.repository.auth;

import org.example.miniprojpetadoptionshelter.entity.auth.RefreshToken;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);

    void deleteByUser(User user);
}
