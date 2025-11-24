package org.example.miniprojpetadoptionshelter.controller.fromAnimal;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.dto.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fosterCare")
@RequiredArgsConstructor
public class FosterController {

    private final FosterService fosterService;

    @PostMapping
    public ResponseEntity<Void> createFoster(@RequestBody FosterCreateReq req) {
        fosterService.createFoster(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FosterListRes>> getFosterList(@RequestParam Long animalId) {
        return ResponseEntity.ok(fosterService.getFosterList(animalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FosterDetailRes> getFosterDetail(@PathVariable Long id) {
        return ResponseEntity.ok(fosterService.getFosterDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFoster(@PathVariable Long id, @RequestBody FosterUpdateReq req) {
        fosterService.updateFoster(id, req);
        return ResponseEntity.ok().build();
    }
}
