import type { VeiwAllUsersResponse, ViewUserProfile } from "@/types/admin/admin.dto";
import { privateApi } from "../common/axiosInstance";
import type { ApiResponse } from "@/types/common/ApiResponse";
import { ADMIN_PATH } from "./admin.path";

export const adminApi = {
  getAllUsers: async (): Promise<VeiwAllUsersResponse> => {
    const res = await privateApi.get<ApiResponse<VeiwAllUsersResponse>>(ADMIN_PATH.LIST);
    return res.data.data;
  },
  getUserProfile: async (userId: number): Promise<ViewUserProfile> => {
    const res = await privateApi.get<ApiResponse<ViewUserProfile>>(ADMIN_PATH.BY_ID(userId));
    return res.data.data;
  }
}