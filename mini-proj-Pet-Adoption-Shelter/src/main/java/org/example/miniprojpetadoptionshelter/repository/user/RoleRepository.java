package org.example.miniprojpetadoptionshelter.repository.user;

import org.example.miniprojpetadoptionshelter.common.enums.RoleType;
import org.example.miniprojpetadoptionshelter.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, RoleType> {

    Optional<Role> findByRoleName(RoleType roleName);
}
