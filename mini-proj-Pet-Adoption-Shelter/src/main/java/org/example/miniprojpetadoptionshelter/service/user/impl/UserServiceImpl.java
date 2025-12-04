package org.example.miniprojpetadoptionshelter.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ErrorCode;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.user.request.UpdateMyProfileRequestDto;
import org.example.miniprojpetadoptionshelter.dto.user.response.MyProfileResponseDto;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.exception.BusinessException;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public ResponseDto<MyProfileResponseDto> getMyProfile(UserPrincipal principal) {

        Long id = principal.getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        MyProfileResponseDto dto = MyProfileResponseDto.from(user);

        return ResponseDto.success(dto);
    }

    @Override
    public ResponseDto<Void> updateMyProfile(UpdateMyProfileRequestDto request, UserPrincipal principal) {

        Long id = principal.getId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(request.name(), request.email(), request.phone());

        if (request.profileFile() != null) {
            user.changeProfileFile(request.profileFile());
        }

        userRepository.save(user);

        return ResponseDto.success("프로필 수정 완료");
    }
}
