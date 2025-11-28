package org.example.miniprojpetadoptionshelter.service.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCancelReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCreateReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationRejectReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationDetailRes;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

import java.time.LocalDate;
import java.util.List;

public interface ApplicationService {
    ResponseDto<Void> createApplication(UserPrincipal principal, @Valid ApplicationCreateReq req);
    ResponseDto<List<ApplicationListRes>> getApplications(User principal, Long animalId, Long applicantId, ApplicationStatus status, LocalDate from, LocalDate to);
    ResponseDto<ApplicationDetailRes> getApplicationById(User principal, @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId);

    ResponseDto<Void> changeApplicationStatusToCanceledById(User principal, @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId, @Valid ApplicationCancelReq req);
    ResponseDto<Void> changeApplicationStatusToReviewById(User principal, @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId, @Valid ApplicationUpdateReq req);
    ResponseDto<Void> changeApplicationStatusToApprovedById(User principal, @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId);
    ResponseDto<Void> changeApplicationStatusToRejectedById(User principal, @Positive(message = "Id는 1이상이어야 합니다.") Long applicationId, @Valid ApplicationRejectReq req);
}
