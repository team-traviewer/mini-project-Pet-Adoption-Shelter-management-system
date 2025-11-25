package org.example.miniprojpetadoptionshelter.service.fromAnimal.impl;

import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.service.fromAnimal.FosterService;

import java.util.List;

public class FosterServiceImpl implements FosterService {
    @Override
    public void createFoster(FosterCreateReq req) {

    }

    @Override
    public List<FosterListRes> getFosterList(Long animalId) {
        return List.of();
    }

    @Override
    public FosterDetailRes getFosterDetail(Long id) {
        return null;
    }

    @Override
    public void updateFoster(Long id, FosterUpdateReq req) {

    }
}
