package org.example.miniprojpetadoptionshelter.dto.admin.request;

import org.example.miniprojpetadoptionshelter.common.enums.RoleType;

public record RoleModifyRequest(
    RoleType roleName
) {
}
