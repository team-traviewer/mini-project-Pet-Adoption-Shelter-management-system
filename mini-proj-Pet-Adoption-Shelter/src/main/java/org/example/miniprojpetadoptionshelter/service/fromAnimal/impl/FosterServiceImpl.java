package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import org.example.miniprojpetadoptionshelter.common.enums.FosterStatus;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCancelReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCloseReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;

import java.time.LocalDate;
import java.util.List;

public class FosterServiceImpl implements FosterService {
    @Override
    public ResponseDto<Void> createFoster(UserPrincipal principal, FosterCreateReq req) {

        return null;
    }

    @Override
    public ResponseDto<List<FosterListRes>> getFosterList(UserPrincipal principal, Long fosterUserId, FosterStatus status, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public ResponseDto<FosterDetailRes> getFosterDetail(Long fosterId, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<Void> closeFoster(Long fosterId, UserPrincipal principal, FosterCloseReq req) {
        return null;
    }

    @Override
    public ResponseDto<Void> cancelFoster(Long fosterId, UserPrincipal principal, FosterCancelReq req) {
        return null;
    }
}
