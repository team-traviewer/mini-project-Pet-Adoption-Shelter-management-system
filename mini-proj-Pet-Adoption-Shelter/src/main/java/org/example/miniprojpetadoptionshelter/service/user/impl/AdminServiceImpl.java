package org.example.miniprojpetadoptionshelter.service.user.impl;

import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.GrantRoleRequest;
import org.example.miniprojpetadoptionshelter.dto.admin.response.RoleListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserProfileResponse;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.user.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public List<ResponseDto<RoleListResponse>> getAllRole(String userId, UserPrincipal principal) {
        return List.of();
    }

    @Override
    public ResponseDto<Void> grantRole(String userId, GrantRoleRequest request, UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<Void> revokeRole(String userId, String roleName, UserPrincipal principal) {
        return null;
    }

    @Override
    public List<ResponseDto<UserListResponse>> getAllUsers(UserPrincipal principal) {
        return List.of();
    }

    @Override
    public ResponseDto<UserProfileResponse> getUserProfile(String userId, UserPrincipal principal) {
        return null;
    }
}
