package org.example.miniprojpetadoptionshelter.service.fromAnimal;

import jakarta.validation.constraints.Positive;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCancelReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCloseReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FosterService {
    ResponseDto<Void> createFoster(UserPrincipal principal, FosterCreateReq req);
    ResponseDto<List<FosterListRes>> getFosterList(UserPrincipal principal);
    ResponseDto<FosterDetailRes> getFosterDetail(@Positive(message = "Id는 1이상이어야 합니다.") Long fosterId, UserPrincipal principal);
    ResponseDto<Void> closeFoster(@Positive(message = "Id는 1이상이어야 합니다.") Long fosterId, UserPrincipal principal, FosterCloseReq req);
    ResponseDto<Void> cancelFoster(@Positive(message = "Id는 1이상이어야 합니다.") Long fosterId, UserPrincipal principal, FosterCancelReq req);
}
