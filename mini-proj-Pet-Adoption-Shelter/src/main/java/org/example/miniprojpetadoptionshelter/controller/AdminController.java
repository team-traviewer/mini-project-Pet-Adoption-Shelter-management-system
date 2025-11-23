package org.example.miniprojpetadoptionshelter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.AdminApi;
import org.example.miniprojpetadoptionshelter.common.apis.RoleApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.GrantRoleRequest;
import org.example.miniprojpetadoptionshelter.dto.admin.response.RoleListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserProfileResponse;
import org.example.miniprojpetadoptionshelter.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private AdminService adminService;

    @GetMapping(RoleApi.BY_ID)
    public ResponseEntity<List<ResponseDto<RoleListResponse>>> getAllRole(
            @RequestParam("userId") String userId
    ) {
        List<ResponseDto<RoleListResponse>> response = adminService.getAllRole(userId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(RoleApi.BY_ID)
    public ResponseEntity<ResponseDto<Void>> grantRole(
            @RequestParam("userId") String userId,
            @Valid @RequestBody GrantRoleRequest request
            ) {
        ResponseDto<Void> response = adminService.grantRole(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping(RoleApi.BY_ID_ROLE)
    public ResponseEntity<ResponseDto<Void>> revokeRole(
            @RequestParam("userId") String userId,
            @RequestParam("roleName") String roleName
    ) {
        ResponseDto<Void> response = adminService.revokeRole(userId, roleName);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(AdminApi.LIST)
    public ResponseEntity<List<ResponseDto<UserListResponse>>> getAllUsers() {
        List<ResponseDto<UserListResponse>> response = adminService.getAllUsers();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(AdminApi.BY_ID)
    public ResponseEntity<ResponseDto<UserProfileResponse>> getUserProfile(
            @RequestParam("userId") String userId
    ) {
        ResponseDto<UserProfileResponse> response = adminService.getUserProfile();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    
}
