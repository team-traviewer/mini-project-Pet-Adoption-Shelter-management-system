package org.example.miniprojpetadoptionshelter.service;

import org.example.miniprojpetadoptionshelter.dto.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.intake.response.IntakeListRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntakeService {
    void createIntake(IntakeCreateReq req);

    List<IntakeListRes> getIntakeList(Long animalId);

    IntakeDetailRes getIntakeDetail(Long id);

    void updateIntake(Long id, IntakeUpdateReq req);
}
