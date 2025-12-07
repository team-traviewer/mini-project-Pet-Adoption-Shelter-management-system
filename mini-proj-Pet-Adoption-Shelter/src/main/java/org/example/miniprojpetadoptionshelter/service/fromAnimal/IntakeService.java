package org.example.miniprojpetadoptionshelter.service.fromAnimal;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntakeService {

    ResponseDto<Void> createIntake(Long animalId, IntakeCreateReq request);

    ResponseDto<List<IntakeListRes>> getIntakeList();

    ResponseDto<IntakeDetailRes> getIntakeDetail(Long id);

    ResponseDto<Void> updateIntake(Long id, IntakeUpdateReq request);
}
