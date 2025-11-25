package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.IntakeService;

import java.util.List;

public class IntakeServiceImpl implements IntakeService {
    public IntakeServiceImpl() {
        super();
    }

    @Override
    public void createIntake(IntakeCreateReq req) {

    }

    @Override
    public List<IntakeListRes> getIntakeList(Long animalId) {
        return List.of();
    }

    @Override
    public IntakeDetailRes getIntakeDetail(Long id) {
        return null;
    }

    @Override
    public void updateIntake(Long id, IntakeUpdateReq req) {

    }
}
