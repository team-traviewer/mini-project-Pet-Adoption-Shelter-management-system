import type { LoginRequest, LoginResponse, LogoutRequest, PasswordResetRequest, PasswordVerifyResponse, RefreshRequest, SignUpRequest, SignUpResponse } from "@/types/auth/auth.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { AUTH_PATH } from "./auth.path";
import type { ApiResponse } from "@/types/common/ApiResponse";

export const authApi = {
  signUp: async (req: SignUpRequest): Promise<SignUpResponse> => {
    const res = await publicApi.post<ApiResponse<SignUpResponse>>(AUTH_PATH.SIGNUP, req);
    return res.data.data;
  },

  login: async (req: LoginRequest): Promise<LoginResponse> => {
    const res = await publicApi.
    post<ApiResponse<LoginResponse>>(
      AUTH_PATH.LOGIN, req
    );
    return res.data.data;
  },

  logout: async (req: LogoutRequest) => {
    await privateApi.post<void>(AUTH_PATH.LOGOUT, req);
  },

  refresh: async (req: RefreshRequest) => {
    await privateApi.post<void>(AUTH_PATH.REFRESH, req);
  },

  passwordReset: async (req: PasswordResetRequest) => {
    await privateApi.post<void>(AUTH_PATH.PASSWORD_RESET, req);
  },

  passwordVerify: async (): Promise<PasswordVerifyResponse> => {
    const res = await privateApi.get<ApiResponse<PasswordVerifyResponse>>(AUTH_PATH.VERIFY);
    return res.data.data;
  }
}