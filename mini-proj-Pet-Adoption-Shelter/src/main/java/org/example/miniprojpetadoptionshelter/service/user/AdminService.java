package org.example.miniprojpetadoptionshelter.service.user;

import jakarta.validation.Valid;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.RoleModifyRequest;
import org.example.miniprojpetadoptionshelter.dto.admin.response.RoleListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserProfileResponse;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

import java.util.List;

public interface AdminService {

    List<ResponseDto<RoleListResponse>> getAllRole(Long userId);

    ResponseDto<Void> grantRole(Long userId, @Valid RoleModifyRequest request);

    ResponseDto<Void> revokeRole(Long userId, RoleModifyRequest request);

    List<ResponseDto<UserListResponse>> getAllUsers(UserPrincipal principal);

    ResponseDto<UserProfileResponse> getUserProfile(Long userId);
}
