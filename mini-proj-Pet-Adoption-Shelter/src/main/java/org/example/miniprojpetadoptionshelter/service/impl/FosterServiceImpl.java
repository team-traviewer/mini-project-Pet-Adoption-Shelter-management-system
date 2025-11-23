package org.example.miniprojpetadoptionshelter.service.impl;

import org.example.miniprojpetadoptionshelter.dto.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.service.FosterService;

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
