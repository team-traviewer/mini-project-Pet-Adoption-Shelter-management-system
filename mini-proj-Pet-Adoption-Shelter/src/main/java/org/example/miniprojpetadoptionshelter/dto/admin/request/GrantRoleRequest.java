package org.example.miniprojpetadoptionshelter.dto.admin.request;

import org.example.miniprojpetadoptionshelter.common.enums.RoleType;

public record GrantRoleRequest(
        RoleType roleName,
        RoleType roleId,
        RoleType roleNickname,
        RoleType roleEmail
) {
}
