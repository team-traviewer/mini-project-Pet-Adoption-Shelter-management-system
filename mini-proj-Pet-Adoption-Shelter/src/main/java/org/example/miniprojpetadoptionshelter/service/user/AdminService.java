package org.example.miniprojpetadoptionshelter.service.user;

import jakarta.validation.Valid;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.GrantRoleRequest;
import org.example.miniprojpetadoptionshelter.dto.admin.response.RoleListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserProfileResponse;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;

import java.util.List;

public interface AdminService {

    List<ResponseDto<RoleListResponse>> getAllRole(String userId, UserPrincipal principal);

    ResponseDto<Void> grantRole(String userId, @Valid GrantRoleRequest request, UserPrincipal principal);

    ResponseDto<Void> revokeRole(String userId, String roleName, UserPrincipal principal);

    List<ResponseDto<UserListResponse>> getAllUsers(UserPrincipal principal);

    ResponseDto<UserProfileResponse> getUserProfile(String userId, UserPrincipal principal);
}
