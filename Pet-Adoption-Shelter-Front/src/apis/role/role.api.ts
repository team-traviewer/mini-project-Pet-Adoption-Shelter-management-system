import type { GrantRoleReq, RoleListResponse } from "@/types/role/role.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { AUTH_PATH } from "../auth/auth.path";
import { ROLE_PATH } from "./role.path";
import type { ApiResponse } from "@/types/common/ApiResponse";

export const roleApi = {
  getAllRoles: async(): Promise<RoleListResponse> => {
    const res = await privateApi.get<ApiResponse<RoleListResponse>>(ROLE_PATH.ROOT);
    return res.data.data;
  },
  grantRole: async(req: GrantRoleReq) => {
    await privateApi.post<void>(ROLE_PATH.GRANT_ROLE, req)
  }
  
}