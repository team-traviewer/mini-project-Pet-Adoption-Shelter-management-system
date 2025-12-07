package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ErrorCode;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.entity.animal.Animal;
import org.example.miniprojpetadoptionshelter.entity.fromAnimal.Intake;
import org.example.miniprojpetadoptionshelter.exception.BusinessException;
import org.example.miniprojpetadoptionshelter.repository.animal.AnimalRepository;
import org.example.miniprojpetadoptionshelter.repository.fromAnimal.IntakeRepository;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.IntakeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntakeServiceImpl implements IntakeService {
    private final AnimalRepository animalRepository;
    private final IntakeRepository intakeRepository;

    @Override
    public ResponseDto<Void> createIntake(Long animalId, IntakeCreateReq request) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        Intake intake = Intake.builder()
                .animal(animal)
                .intakeDate(request.intakeDate())
                .intakeReason(request.intakeReason())
                .foundLocation(request.foundLocation())
                .note(request.note())
                .build();

        intakeRepository.save(intake);

        return ResponseDto.success("intake 생성 완료");
    }

    @Override
    public ResponseDto<List<IntakeListRes>> getIntakeList() {

        List<Intake> intakes = intakeRepository.findAll();

        List<IntakeListRes> response = intakes.stream()
                .map(i -> new IntakeListRes(
                        i.getId(),
                        i.getAnimal().getId(),
                        i.getIntakeDate(),
                        i.getIntakeReason()
                ))
                .toList();

        return ResponseDto.success(response);
    }

    @Override
    public ResponseDto<IntakeDetailRes> getIntakeDetail(Long id) {

        Intake intake = intakeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        IntakeDetailRes response = new IntakeDetailRes(
                intake.getId(),
                intake.getAnimal().getId(),
                intake.getIntakeDate(),
                intake.getIntakeReason(),
                intake.getFoundLocation(),
                intake.getNote(),
                intake.getCreatedAt()
        );

        return ResponseDto.success(response);
    }

    @Override
    public ResponseDto<Void> updateIntake(Long id, IntakeUpdateReq request) {

        Intake intake = intakeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        intake.intakeUpdate(request.intakeDate(), request.intakeReason(), request.foundLocation(), request.note());

        return ResponseDto.success(null);
    }
}
