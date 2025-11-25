package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.request.IntakeUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.intake.response.IntakeListRes;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.IntakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intakeRecords")
@RequiredArgsConstructor
public class IntakeController {

    private final IntakeService intakeService;

    @PostMapping
    public ResponseEntity<Void> createIntake(@RequestBody IntakeCreateReq req) {
        intakeService.createIntake(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IntakeListRes>> getIntakeList(@RequestParam Long animalId) {
        return ResponseEntity.ok(intakeService.getIntakeList(animalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntakeDetailRes> getIntakeDetail(@PathVariable Long id) {
        return ResponseEntity.ok(intakeService.getIntakeDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIntake(@PathVariable Long id, @RequestBody IntakeUpdateReq req) {
        intakeService.updateIntake(id, req);
        return ResponseEntity.ok().build();
    }
}
