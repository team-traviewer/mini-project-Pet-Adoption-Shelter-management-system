package org.example.miniprojpetadoptionshelter.controller.animal;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.animal.AnimalApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalCreateReq;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.animal.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping(AnimalApi.ROOT)
    public ResponseEntity<ResponseDto<AnimalRes>> createAnimal(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody AnimalCreateReq request
            ) {
        ResponseDto<AnimalRes> response = animalService.createAnimal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
