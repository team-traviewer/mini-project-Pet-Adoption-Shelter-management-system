package org.example.miniprojpetadoptionshelter.dto.admin.response;

import org.example.miniprojpetadoptionshelter.common.enums.RoleType;
import org.example.miniprojpetadoptionshelter.entity.user.Role;
import org.example.miniprojpetadoptionshelter.entity.user.User;

import java.util.List;

public record RoleListResponse(
        Long userId,
        List<RoleType> roles
) {
    public static RoleListResponse from(User user) {
        return new RoleListResponse(
                user.getId(),
                user.getAllRoles().stream().toList()
        );
    }
}
