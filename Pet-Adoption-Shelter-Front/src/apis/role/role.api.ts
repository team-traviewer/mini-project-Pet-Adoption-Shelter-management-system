import type { GrantRoleReq, RoleListResponse } from "@/types/role/role.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { ROLE_PATH } from "./role.path";
import type { ApiResponse } from "@/types/common/ApiResponse";

export const roleApi = {
  getAllRoles: async(): Promise<RoleListResponse> => {
    const res = await privateApi.get<ApiResponse<RoleListResponse>>(ROLE_PATH.ROOT);
    return res.data.data;
  },
  grantRole: async(userId: number, req: GrantRoleReq) => {
    await privateApi.post<void>(ROLE_PATH.GRANT_ROLE(userId), req);
  },
  revokeRole: async(userId: number, roleName: string) => {
    await privateApi.post<void>(ROLE_PATH.REVOKE_ROLE(userId, roleName));
  }
}