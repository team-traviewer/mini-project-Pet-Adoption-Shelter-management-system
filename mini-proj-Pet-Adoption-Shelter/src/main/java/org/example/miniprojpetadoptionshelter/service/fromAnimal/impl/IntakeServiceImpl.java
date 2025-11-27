package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.IntakeService;

import java.util.List;

public class IntakeServiceImpl implements IntakeService {
    @Override
    public ResponseDto<Void> createIntake(IntakeCreateReq req, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<List<IntakeListRes>> getIntakeList(Long animalId, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<IntakeDetailRes> getIntakeDetail(Long id, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<Void> updateIntake(Long id, IntakeUpdateReq req, UserPrincipal principal) {
        return null;
    }
}
