export interface RoleListDto {
  roleName: string;
}

export type RoleListResponse = RoleListDto[];

export interface GrantRoleReq {
  roleName: string;
}

