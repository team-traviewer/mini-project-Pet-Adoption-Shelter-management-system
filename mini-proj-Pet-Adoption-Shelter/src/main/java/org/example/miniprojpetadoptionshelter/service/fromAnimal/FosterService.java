package org.example.miniprojpetadoptionshelter.service.fromAnimal;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterCreateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.request.FosterUpdateReq;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterDetailRes;
import org.example.miniprojpetadoptionshelter.dto.fromAnimal.foster.response.FosterListRes;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FosterService {
    ResponseDto<Void> createFoster(UserPrincipal principal, FosterCreateReq req);


    ResponseDto<List<FosterListRes>> getFosterList(Long animalId, UserPrincipal principal);

    ResponseDto<FosterDetailRes> getFosterDetail(Long id, UserPrincipal principal);

    ResponseDto<Void> updateFoster(Long id, FosterUpdateReq req, UserPrincipal principal);
}
