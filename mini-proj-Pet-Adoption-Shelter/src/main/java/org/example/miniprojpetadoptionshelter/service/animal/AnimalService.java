package org.example.miniprojpetadoptionshelter.service.animal;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalCreateReq;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.animal.response.AnimalDetailRes;
import org.example.miniprojpetadoptionshelter.dto.animal.response.AnimalListRes;

import java.util.List;

public interface AnimalService {
    ResponseDto<Void> createAnimal(AnimalCreateReq request);

    ResponseDto<List<AnimalListRes>> getAllAnimals();

    ResponseDto<AnimalDetailRes> getAnimalById(Long animalId);

    ResponseDto<Void> updateAnimal(Long animalId, AnimalUpdateReq request);
}
