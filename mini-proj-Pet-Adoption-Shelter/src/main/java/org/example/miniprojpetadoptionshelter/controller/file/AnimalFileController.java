package org.example.miniprojpetadoptionshelter.controller.file;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.animal.AnimalApi;
import org.example.miniprojpetadoptionshelter.service.animal.impl.AnimalServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnimalFileController {
    private final AnimalServiceImpl animalService;

    @PostMapping()
}
