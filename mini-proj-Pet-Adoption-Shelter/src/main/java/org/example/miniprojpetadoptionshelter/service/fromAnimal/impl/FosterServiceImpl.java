package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;

import java.util.List;

public class FosterServiceImpl implements FosterService {
    @Override
    public ResponseDto<Void> createFoster(UserPrincipal principal, FosterCreateReq req) {

        return null;
    }

    @Override
    public ResponseDto<List<FosterListRes>> getFosterList(Long animalId, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<FosterDetailRes> getFosterDetail(Long id, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<Void> updateFoster(Long id, FosterUpdateReq req, UserPrincipal principal) {
        return null;
    }
}
