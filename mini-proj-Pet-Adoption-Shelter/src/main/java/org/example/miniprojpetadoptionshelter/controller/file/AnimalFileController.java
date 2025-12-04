package org.example.miniprojpetadoptionshelter.controller.file;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.animal.AnimalFileApi;
import org.example.miniprojpetadoptionshelter.common.apis.file.FileApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.animal.AnimalFileListDto;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalImgUpdateReq;
import org.example.miniprojpetadoptionshelter.service.file.AnimalFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnimalFileController {
    private final AnimalFileService animalFileService;

    @PostMapping(AnimalFileApi.UPLOAD)
    public ResponseEntity<ResponseDto<Void>> uploadAnimalImg(
            @PathVariable Long animalId,
            @RequestParam("files")List<MultipartFile> files
            ){
        ResponseDto<Void> result = animalFileService.uploadAnimalImg(animalId, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(AnimalFileApi.LIST)
    public ResponseEntity<ResponseDto<List<AnimalFileListDto>>> getAnimalFiles(
            @PathVariable Long animalId
    ){
        ResponseDto<List<AnimalFileListDto>> result = animalFileService.getAnimalImgById(animalId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(FileApi.DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteAnimalImg(@PathVariable Long fileId){
        ResponseDto<Void> result = animalFileService.deleteAnimalImg(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(value = AnimalFileApi.UPLOAD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAnimalImg(@PathVariable Long animalId, @ModelAttribute AnimalImgUpdateReq dto) {
        ResponseDto<Void> result = animalFileService.updateAnimalImg(animalId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
