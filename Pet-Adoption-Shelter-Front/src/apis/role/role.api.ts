import type { GrantRoleReq, RoleListResponse } from "@/types/role/role.dto";
import { privateApi } from "../common/axiosInstance";
import { ROLE_PATH } from "./role.path";
import type { ApiResponse } from "@/types/common/ApiResponse";

export const roleApi = {
  getAllRoles: async(userId: number): Promise<RoleListResponse> => {
    const res = await privateApi.get<ApiResponse<RoleListResponse>>(ROLE_PATH.BY_ID(userId));
    return res.data.data;
  },
  grantRole: async(userId: number, req: GrantRoleReq) => {
    await privateApi.post<void>(ROLE_PATH.BY_ID(userId), req);
  },
  revokeRole: async(userId: number, roleName: string) => {
    await privateApi.post<void>(ROLE_PATH.BY_ID_ROLE(userId, roleName));
  }
}