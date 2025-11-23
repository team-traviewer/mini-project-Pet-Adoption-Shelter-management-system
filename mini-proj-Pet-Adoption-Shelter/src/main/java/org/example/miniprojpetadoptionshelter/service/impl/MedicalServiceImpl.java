package org.example.miniprojpetadoptionshelter.service.impl;

import org.example.miniprojpetadoptionshelter.dto.medical.request.MedicalCreateReq;
import org.example.miniprojpetadoptionshelter.dto.medical.request.MedicalUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.medical.response.MedicalDetailRes;
import org.example.miniprojpetadoptionshelter.dto.medical.response.MedicalListRes;
import org.example.miniprojpetadoptionshelter.service.MedicalService;

import java.util.List;

public class MedicalServiceImpl implements MedicalService {
    @Override
    public MedicalDetailRes getMedicalDetail(Long id) {
        return null;
    }

    @Override
    public List<MedicalListRes> getMedicalList(Long animalId) {
        return List.of();
    }

    @Override
    public void createMedical(MedicalCreateReq req) {

    }

    @Override
    public void updateMedical(Long id, MedicalUpdateReq req) {

    }
}
