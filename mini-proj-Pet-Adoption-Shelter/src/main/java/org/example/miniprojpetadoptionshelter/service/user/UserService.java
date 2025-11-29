package org.example.miniprojpetadoptionshelter.service.user;

import jakarta.validation.Valid;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.user.request.UpdateMyProfileRequestDto;
import org.example.miniprojpetadoptionshelter.dto.user.response.MyProfileResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

public interface UserService {

    ResponseDto<MyProfileResponseDto> getMyProfile(UserPrincipal principal);

    ResponseDto<Void> updateMyProfile(@Valid UpdateMyProfileRequestDto request, UserPrincipal principal);
}
