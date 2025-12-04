package org.example.miniprojpetadoptionshelter.service.application.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ApplicationStatus;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCancelReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationCreateReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationRejectReq;
import org.example.miniprojpetadoptionshelter.dto.application.request.ApplicationUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationDetailRes;
import org.example.miniprojpetadoptionshelter.dto.application.response.ApplicationListRes;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.repository.animal.AnimalRepository;
import org.example.miniprojpetadoptionshelter.repository.application.ApplicationRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.application.ApplicationService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Override
    public ResponseDto<Void> createApplication(UserPrincipal principal, ApplicationCreateReq req) {

        return ResponseDto.success("입양신청이 성공하였습니다.");
    }

    @Override
    public ResponseDto<List<ApplicationListRes>> getApplications(UserPrincipal principal, Long animalId, Long applicantId, ApplicationStatus status, LocalDate from, LocalDate to) {


        return ResponseDto.success("조회 성공", data);
    }

    @Override
    public ResponseDto<ApplicationDetailRes> getApplicationById(UserPrincipal principal, Long applicationId) {
        return ResponseDto.success("조회 성공", data);
    }

    @Override
    public ResponseDto<Void> cancelApplicationById(UserPrincipal principal, Long applicationId, ApplicationCancelReq req) {
        return ResponseDto.success("SUCCESS", null);
    }

    @Override
    public ResponseDto<Void> reviewApplicationById(UserPrincipal principal, Long applicationId, ApplicationUpdateReq req) {
        if (!isStaff(principal)) {
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return ResponseDto.success("SUCCESS", null);
    }

    @Override
    public ResponseDto<Void> approveApplicationById(UserPrincipal principal, Long applicationId) {
        return ResponseDto.success("SUCCESS", null);
    }

    @Override
    public ResponseDto<Void> rejectApplicationById(UserPrincipal principal, Long applicationId, ApplicationRejectReq req) {
        return ResponseDto.success("SUCCESS", null);
    }

    /** 권한 체크 메서드 */

    private boolean isStaff(UserPrincipal principal) {
        return principal.getAuthorities().stream()
                .anyMatch(auth ->
                        auth.getAuthority().equals("ROLE_STAFF"));

    }
}
