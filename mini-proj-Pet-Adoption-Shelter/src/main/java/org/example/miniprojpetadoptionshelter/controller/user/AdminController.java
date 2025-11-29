package org.example.miniprojpetadoptionshelter.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.auth.AdminApi;
import org.example.miniprojpetadoptionshelter.common.apis.user.RoleApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.admin.request.GrantRoleRequest;
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
            @RequestParam("userId") String userId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        List<ResponseDto<RoleListResponse>> response = adminService.getAllRole(userId, principal);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoleApi.BY_ID)
    public ResponseEntity<ResponseDto<Void>> grantRole(
            @RequestParam("userId") String userId,
            @Valid @RequestBody GrantRoleRequest request,
            @AuthenticationPrincipal UserPrincipal principal
            ) {
        ResponseDto<Void> response = adminService.grantRole(userId, request, principal);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoleApi.BY_ID_ROLE)
    public ResponseEntity<ResponseDto<Void>> revokeRole(
            @RequestParam("userId") String userId,
            @RequestParam("roleName") String roleName,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<Void> response = adminService.revokeRole(userId, roleName, principal);
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
            @RequestParam("userId") String userId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<UserProfileResponse> response = adminService.getUserProfile(userId, principal);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
