package org.example.miniprojpetadoptionshelter.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.apis.user.UserApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.user.request.UpdateMyProfileRequestDto;
import org.example.miniprojpetadoptionshelter.dto.user.response.MyProfileResponseDto;
import org.example.miniprojpetadoptionshelter.security.user.UserPrincipal;
import org.example.miniprojpetadoptionshelter.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserApi.ROOT)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @GetMapping(UserApi.ME)
    public ResponseEntity<ResponseDto<MyProfileResponseDto>> getMyProfile(
            @AuthenticationPrincipal UserPrincipal principal
            ) {
        ResponseDto<MyProfileResponseDto> response = userService.getMyProfile(principal);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyRole('USER','STAFF','ADMIN')")
    @PutMapping(UserApi.ME)
    public ResponseEntity<ResponseDto<Void>> updateMyProfile(
            @Valid @RequestBody UpdateMyProfileRequestDto request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<Void> response = userService.updateMyProfile(request, principal);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
