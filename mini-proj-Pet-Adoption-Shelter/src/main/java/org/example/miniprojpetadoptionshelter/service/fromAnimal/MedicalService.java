package org.example.miniprojpetadoptionshelter.service.fromAnimal;

import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.request.MedicalCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.request.MedicalUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.response.MedicalDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.medical.response.MedicalListRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalService {
    MedicalDetailRes getMedicalDetail(Long id);

    List<MedicalListRes> getMedicalList(Long animalId);

    void createMedical(MedicalCreateReq req);

    void updateMedical(Long id, MedicalUpdateReq req);
}
