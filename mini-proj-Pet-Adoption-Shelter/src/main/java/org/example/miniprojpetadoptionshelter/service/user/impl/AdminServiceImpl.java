package org.example.miniprojpetadoptionshelter.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.ErrorCode;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.RoleModifyRequest;
import org.example.miniprojpetadoptionshelter.dto.admin.response.RoleListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserProfileResponse;
import org.example.miniprojpetadoptionshelter.entity.user.Role;
import org.example.miniprojpetadoptionshelter.entity.user.User;
import org.example.miniprojpetadoptionshelter.exception.BusinessException;
import org.example.miniprojpetadoptionshelter.repository.user.RoleRepository;
import org.example.miniprojpetadoptionshelter.repository.user.UserRepository;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.user.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<ResponseDto<RoleListResponse>> getAllRole(Long userId) {

        List<ResponseDto<RoleListResponse>> data = null;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        data = user.getAllRoles().stream()
                .map(role -> ResponseDto.success(
                        new RoleListResponse(user.getId(), List.of(role))
                ))
                .toList();

        return data;
    }

    @Override
    public ResponseDto<Void> grantRole(Long userId, RoleModifyRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Role role = roleRepository.findByRoleName(request.roleName())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_TYPE));

        user.grantRole(role);

        return ResponseDto.success("권한이 부여되었습니다.");
    }

    @Override
    public ResponseDto<Void> revokeRole(Long userId, RoleModifyRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Role role = roleRepository.findByRoleName(request.roleName())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_TYPE));

        user.revokeRole(role);

        return ResponseDto.success("권한이 해제되었습니다.");
    }

    @Override
    public List<ResponseDto<UserListResponse>> getAllUsers(UserPrincipal principal) {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> ResponseDto.success(UserListResponse.from(user)))
                .toList();
    }

    @Override
    public ResponseDto<UserProfileResponse> getUserProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        UserProfileResponse data = UserProfileResponse.from(user);

        return ResponseDto.success(data);
    }
}
