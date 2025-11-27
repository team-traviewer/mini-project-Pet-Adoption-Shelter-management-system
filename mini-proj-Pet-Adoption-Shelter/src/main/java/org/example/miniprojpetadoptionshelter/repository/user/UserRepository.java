package org.example.miniprojpetadoptionshelter.repository.user;


import org.example.miniprojpetadoptionshelter.common.enums.AuthProvider;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId);

    Optional<User> findByLoginId(String loginId);

    @Query("""
        select distinct u
        from User u
        left join fetch u.userRoles ur
            left join fetch ur.role r
        where u.loginId = :loginId

""")
    Optional<User> findWithRolesByLoginId(@Param("loginId") String loginId);
}