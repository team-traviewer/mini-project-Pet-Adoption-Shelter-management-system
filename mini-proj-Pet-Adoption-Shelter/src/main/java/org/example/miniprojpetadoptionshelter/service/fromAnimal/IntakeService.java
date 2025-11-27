package org.example.miniprojpetadoptionshelter.service.fromAnimal;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntakeService {

    ResponseDto<Void> createIntake(IntakeCreateReq req, UserPrincipal principal);

    ResponseDto<List<IntakeListRes>> getIntakeList(Long animalId, UserPrincipal principal);

    ResponseDto<IntakeDetailRes> getIntakeDetail(Long id, UserPrincipal principal);

    ResponseDto<Void> updateIntake(Long id, IntakeUpdateReq req, UserPrincipal principal);
}
