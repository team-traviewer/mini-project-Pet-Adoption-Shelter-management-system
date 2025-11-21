import type { FosterCreateReq, FosterDetailResponse, FosterListResponse} from "@/types/foster/foster.dto";
import { FOSTER_PATH, type Status } from "./foster.path";
import type { ApiResponse } from "@/types/common/ApiResponse";
import { privateApi, publicApi } from "../common/axiosInstance";

export const IntakeApi = {
  // 임시보호 배정 생성
  createFosterInfo: async (req: FosterCreateReq): Promise<void> => {
    const res = await privateApi.post<ApiResponse<void>>(FOSTER_PATH.ROOT, req);
    return res.data.data;
  },

  // 임시보호 목록
  getFosterList : async () => {
    const res = await publicApi.get<FosterListResponse>(FOSTER_PATH.ROOT);
    return res.data;
  },

  // 임시보호 상세 조회
  getIntakeDetail : async (fosterId: number) => {
    const res = await publicApi.get<FosterDetailResponse>(FOSTER_PATH.BY_ID(fosterId));
    return res.data;
  },

  // 임시보호 취소/종료
  updateIntakeInfo: async (fosterId: number, status: Status) => {
    const res = await privateApi.put<void>(
      FOSTER_PATH.CHANGE_STATUS(fosterId, status));
    return res.data;
  }
}