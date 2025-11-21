export interface RoleListDto {
  userId: number;
  roleName: string;
}

export type RoleListResponse = RoleListDto[];

export interface GrantRoleReq {
  roleName: string;
}