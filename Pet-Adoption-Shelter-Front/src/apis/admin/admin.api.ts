import type { UserListResponse, UserProfileResponse } from "@/types/admin/admin.dto";
import { privateApi } from "../common/axiosInstance";
import type { ApiResponse } from "@/types/common/ApiResponse";
import { ADMIN_PATH } from "./admin.path";

export const adminApi = {
  getAllUsers: async (): Promise<UserListResponse> => {
    const res = await privateApi.get<ApiResponse<UserListResponse>>(ADMIN_PATH.LIST);
    return res.data.data;
  },
  getUserProfile: async (userId: number): Promise<UserProfileResponse> => {
    const res = await privateApi.get<ApiResponse<UserProfileResponse>>(ADMIN_PATH.BY_ID(userId));
    return res.data.data;
  }
}