package org.example.miniprojpetadoptionshelter.service.user.impl;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.user.request.UpdateMyProfileRequestDto;
import org.example.miniprojpetadoptionshelter.dto.user.response.MyProfileResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseDto<MyProfileResponseDto> getMyProfile(UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<Void> updateMyProfile(UpdateMyProfileRequestDto request, UserPrincipal principal) {
        return null;
    }
}
