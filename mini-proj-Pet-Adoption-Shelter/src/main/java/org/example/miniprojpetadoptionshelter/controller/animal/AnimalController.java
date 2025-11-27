package org.example.miniprojpetadoptionshelter.controller.animal;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.animal.AnimalApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalCreateReq;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.animal.response.AnimalDetailRes;
import org.example.miniprojpetadoptionshelter.dto.animal.response.AnimalListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.animal.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping(AnimalApi.ROOT)
    public ResponseEntity<ResponseDto<Void>> createAnimal(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody AnimalCreateReq request
            ) {
        ResponseDto<Void> response = animalService.createAnimal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(AnimalApi.ROOT)
    public ResponseEntity<ResponseDto<List<AnimalListRes>>> getAllAnimals() {
        ResponseDto<List<AnimalListRes>> response = animalService.getAllAnimals();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(AnimalApi.BY_ID)
    public ResponseEntity<ResponseDto<AnimalDetailRes>> getAnimalById(
            @PathVariable Long animalId
    ) {
        ResponseDto<AnimalDetailRes> response = animalService.getAnimalById(animalId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(AnimalApi.BY_ID)
    public ResponseEntity<ResponseDto<Void>> updateAnimalById(
            @PathVariable Long animalId,
            @RequestBody AnimalUpdateReq request
    ){
        ResponseDto<Void> response = animalService.updateAnimal(animalId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
