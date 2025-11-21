package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.miniprojpetadoptionshelter.common.enums.RoleType;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_name")
    private RoleType roleName;

    public Role(RoleType roleName) { this.roleName = roleName; }
}
