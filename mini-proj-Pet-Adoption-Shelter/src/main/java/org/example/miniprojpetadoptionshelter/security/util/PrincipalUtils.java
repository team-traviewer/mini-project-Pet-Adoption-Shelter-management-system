package org.example.miniprojpetadoptionshelter.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ErrorCode;
import org.example.miniprojpetadoptionshelter.exception.BusinessException;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PrincipalUtils {

    public static void validateActive(UserPrincipal principal) {

        // 1) 로그인 안 한 상태 (null principal)
        if (principal == null) {
            throw new BusinessException(ErrorCode.INVALID_AUTH);
        }

        // 2) 비활성화된 계정
        if (!principal.isEnabled()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }

        // 3) 잠긴 계정
        if (!principal.isAccountNonLocked()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }

        // 4) 계정 기간 만료
        if (!principal.isAccountNonExpired()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }

        // 5) 비밀번호(자격 증명) 만료
        if (!principal.isCredentialsNonExpired()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }
    }
}