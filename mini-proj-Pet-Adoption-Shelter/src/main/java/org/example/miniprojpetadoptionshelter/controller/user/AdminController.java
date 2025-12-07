package org.example.miniprojpetadoptionshelter.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.auth.AdminApi;
import org.example.miniprojpetadoptionshelter.common.apis.user.RoleApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.RoleModifyRequest;
import org.example.miniprojpetadoptionshelter.dto.admin.response.RoleListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserListResponse;
import org.example.miniprojpetadoptionshelter.dto.admin.response.UserProfileResponse;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.user.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(RoleApi.BY_ID)
    public ResponseEntity<List<ResponseDto<RoleListResponse>>> getAllRole(
            @PathVariable("userId") Long userId
    ) {
        List<ResponseDto<RoleListResponse>> response = adminService.getAllRole(userId);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoleApi.BY_ID)
    public ResponseEntity<ResponseDto<Void>> grantRole(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody RoleModifyRequest request
            ) {
        ResponseDto<Void> response = adminService.grantRole(userId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoleApi.BY_ID_ROLE)
    public ResponseEntity<ResponseDto<Void>> revokeRole(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody RoleModifyRequest request
    ) {
        ResponseDto<Void> response = adminService.revokeRole(userId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(AdminApi.LIST)
    public ResponseEntity<List<ResponseDto<UserListResponse>>> getAllUsers(
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        List<ResponseDto<UserListResponse>> response = adminService.getAllUsers(principal);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(AdminApi.BY_ID)
    public ResponseEntity<ResponseDto<UserProfileResponse>> getUserProfile(
            @PathVariable("userId") Long userId
    ) {
        ResponseDto<UserProfileResponse> response = adminService.getUserProfile(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
