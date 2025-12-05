package org.example.miniprojpetadoptionshelter.service.fromAnimal;

import jakarta.validation.constraints.Positive;
import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCancelReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCloseReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface FosterService {
    ResponseDto<Void> createFoster(UserPrincipal principal, FosterCreateReq req);
    ResponseDto<FosterListRes.PageResponse> getFosters(UserPrincipal principal, int page, int size, String[] sort, Long fosterUserId, FosterStatus status, LocalDate startDate, LocalDate endDate);
    ResponseDto<FosterDetailRes> getFosterById( UserPrincipal principal, @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId);

    ResponseDto<Void> closeFoster(UserPrincipal principal, @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId,  FosterCloseReq req);
    ResponseDto<Void> cancelFoster(UserPrincipal principal, @Positive(message = "Id는 1이상이어야 합니다.") Long fosterId, FosterCancelReq req);
}
