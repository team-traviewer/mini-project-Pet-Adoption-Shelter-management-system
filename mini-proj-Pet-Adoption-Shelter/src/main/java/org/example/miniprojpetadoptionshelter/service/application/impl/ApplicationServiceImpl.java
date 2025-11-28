package org.example.miniprojpetadoptionshelter.service.application.impl;

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
import org.example.miniprojpetadoptionshelter.service.application.ApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public ResponseDto<Void> createApplication(UserPrincipal principal, ApplicationCreateReq req) {
        return null;
    }

    @Override
    public ResponseDto<List<ApplicationListRes>> getApplications(User principal, Long animalId, Long applicantId, ApplicationStatus status, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public ResponseDto<ApplicationDetailRes> getApplicationById(User principal, Long applicationId) {
        return null;
    }

    @Override
    public ResponseDto<Void> changeApplicationStatusToCanceledById(User principal, Long applicationId, ApplicationCancelReq req) {
        return null;
    }

    @Override
    public ResponseDto<Void> changeApplicationStatusToReviewById(User principal, Long applicationId, ApplicationUpdateReq req) {
        return null;
    }

    @Override
    public ResponseDto<Void> changeApplicationStatusToApprovedById(User principal, Long applicationId) {
        return null;
    }

    @Override
    public ResponseDto<Void> changeApplicationStatusToRejectedById(User principal, Long applicationId, ApplicationRejectReq req) {
        return null;
    }
}
