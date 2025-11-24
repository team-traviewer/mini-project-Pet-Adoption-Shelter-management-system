package org.example.miniprojpetadoptionshelter.controller.user;

import jakarta.validation.Valid;
import org.example.miniprojpetadoptionshelter.common.apis.UserApi;
import org.example.miniprojpetadoptionshelter.dto.ResponseDto;
import org.example.miniprojpetadoptionshelter.dto.user.request.UpdateMyProfileRequestDto;
import org.example.miniprojpetadoptionshelter.dto.user.response.MyProfileResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserApi.ROOT)
public class UserController {
    private final UserService userService;

    @GetMapping(UserApi.ME)
    public ResponseEntity<ResponseDto<MyProfileResponseDto>> getMyProfile() {
        ResponseDto<MyProfileResponseDto> response = userService.getMyProfile();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping(UserApi.ME)
    public ResponseEntity<ResponseDto<Void>> updateMyProfile(
            @Valid @RequestBody UpdateMyProfileRequestDto request
    ) {
        ResponseDto<Void> response = userService.updateMyProfile();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
