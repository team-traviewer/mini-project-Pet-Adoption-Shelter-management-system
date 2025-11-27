package org.example.miniprojpetadoptionshelter.service.user;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.user.response.MyProfileResponseDto;

public interface UserService {
    ResponseDto<MyProfileResponseDto> getMyProfile();

    ResponseDto<Void> updateMyProfile();
}
