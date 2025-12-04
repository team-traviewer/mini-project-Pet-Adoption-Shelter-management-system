package org.example.miniprojpetadoptionshelter.service.file;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.animal.AnimalFileListDto;
import org.example.miniprojpetadoptionshelter.dto.animal.request.AnimalImgUpdateReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnimalFileService {
    ResponseDto<Void> uploadAnimalImg(Long animalId, List<MultipartFile> files);

    ResponseDto<List<AnimalFileListDto>> getAnimalImgById(Long animalId);

    ResponseDto<Void> deleteAnimalImg(Long fileId);

    ResponseDto<Void> updateAnimalImg(Long animalId, AnimalImgUpdateReq dto);
}
