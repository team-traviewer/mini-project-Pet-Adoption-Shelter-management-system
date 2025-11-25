package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.request.MedicalCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.request.MedicalUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.response.MedicalDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.response.MedicalListRes;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.MedicalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicalRecords")
@RequiredArgsConstructor
public class MedicalController {

    private final MedicalService medicalService;

    @PostMapping
    public ResponseEntity<Void> createMedical(@RequestBody MedicalCreateReq req) {
        medicalService.createMedical(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MedicalListRes>> getMedicalList(@RequestParam Long animalId) {
        return ResponseEntity.ok(medicalService.getMedicalList(animalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalDetailRes> getMedicalDetail(@PathVariable Long id) {
        return ResponseEntity.ok(medicalService.getMedicalDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedical(@PathVariable Long id, @RequestBody MedicalUpdateReq req) {
        medicalService.updateMedical(id, req);
        return ResponseEntity.ok().build();
    }
}
