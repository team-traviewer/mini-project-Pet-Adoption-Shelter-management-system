import type { MyProfileResponse, UpdateMyProfileRequest } from "@/types/user/user.dto";
import { privateApi } from "../common/axiosInstance";
import type { ApiResponse } from "@/types/common/ApiResponse";
import { USER_PATH } from "./user.path";

export const userApi = {
  viewMyProfile: async (): Promise<MyProfileResponse> => {
    const res = await privateApi.get<ApiResponse<MyProfileResponse>>(USER_PATH.ME);
    return res.data.data;
  },
  updateMyProfile: async (req: UpdateMyProfileRequest) => {
    await privateApi.put<void>(USER_PATH.ME, req);
  },

}