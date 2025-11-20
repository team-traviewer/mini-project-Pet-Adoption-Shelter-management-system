import type { EmailSendReq, LoginReq, LoginRes, PasswordResetReq, PasswordResetRes, SignUpReq, VerifyReq } from "@/types/auth/auth.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { AUTH_PATH } from "./auth.path";
import type { ApiResponse } from "@/types/common/ApiResponse";

export const authApi = {
  signUp: async (req: SignUpReq) => {
    await publicApi.post<void>(AUTH_PATH.SIGNUP, req);
  },
  login: async (req: LoginReq): Promise<LoginRes> => {
    const res = await publicApi.
    post<ApiResponse<LoginRes>>(
      AUTH_PATH.LOGIN, req
    );
    return res.data.data;
  },
  logout: async () => {
    await privateApi.post<void>(AUTH_PATH.LOGOUT);
  },
  refresh: async () => {
    await privateApi.post<void>(AUTH_PATH.REFRESH);
  },
  passwordReset: async (req: PasswordResetReq): Promise<PasswordResetRes> => {
    const res = await privateApi.post<ApiResponse<PasswordResetRes>>(AUTH_PATH.PASSWORD_RESET, req);
    return res.data.data;
  },
  verify: async (req: VerifyReq) => {
    await privateApi.post<void>(AUTH_PATH.VERIFY, req);
  },
  emailSend: async (req: EmailSendReq) => {
    await privateApi.post<void>(AUTH_PATH.EMAIL_SEND, req);
  }

}

