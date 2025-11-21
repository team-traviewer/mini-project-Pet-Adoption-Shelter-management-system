//! application.api.ts

import type{ ApplicationDetailRes,  ApplicationCreateReq,ApplicationListRes} from "@/types/application/application.dto";
import { privateApi } from "../common/axiosInstance";
import { APPLICATION_PATH } from "./application.path";
import type { ApiResponse } from "@/types/common/ApiResponse";
import type { ApplicationStatus } from "@/types/application/application.type";

export const applicationApi = {

  // 입양 신청
  createApplications: async (req: ApplicationCreateReq): Promise<void> => {
    const res = await privateApi.post<ApiResponse<void>>(
      APPLICATION_PATH.ROOT, req
    );
    return res.data.data;
  },

  // 입양 신청 조회
  getApplicationList: async () => {
    const res = await privateApi.get<ApiResponse<ApplicationListRes>>(
      APPLICATION_PATH.ROOT
    );
    return res.data.data;
  }
  ,

  // 입양 신청 상세 조회
  getApplicationDetail: async (applicationId: number) => {
    const res = await privateApi.get<ApiResponse<ApplicationDetailRes>>(
      APPLICATION_PATH.BY_ID(applicationId)
    );
    return res.data.data;
  },

  // 입양 신청 심사 상태 수정
  updateApplicationStatus: async (applicationId: number, status: ApplicationStatus) => {
    const res = await privateApi.put<ApiResponse<void>>(
      APPLICATION_PATH.STATUS(applicationId, status)
    );
    return res.data.data;
  },
};
