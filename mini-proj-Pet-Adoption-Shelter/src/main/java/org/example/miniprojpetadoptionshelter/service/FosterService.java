package org.example.miniprojpetadoptionshelter.service;

import org.example.miniprojpetadoptionshelter.dto.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.foster.response.FosterListRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FosterService {
    void createFoster(FosterCreateReq req);

    List<FosterListRes> getFosterList(Long animalId);

    FosterDetailRes getFosterDetail(Long id);

    void updateFoster(Long id, FosterUpdateReq req);
}
